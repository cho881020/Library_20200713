package kr.co.tjoeun.library_20200713

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        callBtn.setOnClickListener {



//            TedPermission을 이용해서 통화 권한을 허락할건지 질문.

//            권한 승인/거절에 따라 어떤 행동을할지 할일 을 적어둔 변수.
            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {

//                    승인을 받은 상황. => 실제로 전화를 걸자
                    val phoneNum = phoneNumTxt.text.toString()

                    val myUri = Uri.parse("tel:${phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                      최종 거부된상황. 통화를 할 수 없다.

                    Toast.makeText(mContext, "통화 권한이 거부되어 연결 불가합니다.", Toast.LENGTH_SHORT).show()

                }

            }

        }

        goToPhotoViewBtn.setOnClickListener {
            val myIntent = Intent(mContext, ProfilePhotoActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
        val imgUrl = "http://news.kbs.co.kr/data/news/2019/01/16/4117160_uS4.jpg"
        Glide.with(mContext).load(imgUrl).into(profileImg)
    }


}
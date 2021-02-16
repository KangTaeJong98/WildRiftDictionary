package com.taetae98.wildriftdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taetae98.wildriftdictionary.base.BaseActivity
import com.taetae98.wildriftdictionary.data.NewsData
import com.taetae98.wildriftdictionary.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun init() {
        super.init()
        initData()
        moveMainActivity()
    }

    private fun initData() {
        NewsData.getInstance().news
    }

    private fun moveMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
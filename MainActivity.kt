package com.example.app_intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.get
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Welcome",
                "Let's take a step forward for a social cause.",
                R.drawable.logo
            ),
            IntroSlide(

                "What's the Motive of the app?",

                "This app is designed for helping disabled people in their daily life . This " +
                        "app also contains a section of conversion of voice to sign Language, " +
                        "so as to minimise communication gap between any two peoples.",
                R.drawable.images
            ),
            IntroSlide(
                "Explore more!",

                "Wanna know more about the app ? Go Ahead. ",
                R.drawable.third
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object:
        ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position:Int){
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem +1<introSliderAdapter.itemCount)
            {
                introSliderViewPager.currentItem+=1
            }else{
                Intent(applicationContext, AnotherAcitvity:: class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }
        textSkipIntro.setOnClickListener{
            Intent(applicationContext, AnotherAcitvity:: class.java).also{
                startActivity(it)
                finish()
            }
        }
    }
    private fun setupIndicators()
    {
        val indicators=arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i]=ImageView(applicationContext)
            indicators[i].apply{
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView((indicators[i]))
        }
    }
    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView= indicatorsContainer[i] as ImageView
            if(i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
        }
    }
}

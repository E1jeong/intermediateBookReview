package com.fastcampus.intermediatebookreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.fastcampus.intermediatebookreview.databinding.ActivityDetailBinding
import com.fastcampus.intermediatebookreview.model.Book
import com.fastcampus.intermediatebookreview.model.Review

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = getAppDatabase(this)

        val model = intent.getParcelableExtra<Book>("bookModel")

        binding.titleTextView.text = model?.title.orEmpty()
        binding.descriptionTextView.text = model?.description.orEmpty()

        Glide.with(binding.coverImageView.context)
            .load(model?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)

//        Thread {
//            val review = db.reviewDao().getOneReview(model?.id.orEmpty())
//            runOnUiThread {
//                binding.reviewEditText.setText(review?.review.orEmpty())
//            }
//        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        model?.id.orEmpty(),
                        binding.reviewEditText.text.toString()
                    )
                )
            }.start()
        }
    }
}
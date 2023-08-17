package com.example.perqaragames.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.perqaragames.databinding.ActivityDetailBinding
import com.example.perqaragames.model.GameDetailResponse

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var binding: ActivityDetailBinding

    private val presenter by lazy { DetailPresenter(this) }

    private var idGame: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idGame = intent.getIntExtra("id", 0)
        presenter.getDetail(idGame ?: 0)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onGetDetail(data: GameDetailResponse?) {
        Log.e("afas", "detail : $data")
        binding.apply {
            Glide.with(root).load(data?.backgroundImage).into(ivGame)
            tvPublisher.text = data?.publishers?.get(0)?.name ?: ""
            tvNameGame.text = data?.name
            tvReleased.text = "Released date ${data?.released}"
            tvRating.text = data?.rating.toString()
            tvPlaytime.text = data?.playtime.toString()
        }
    }

    companion object {
        fun startActivity(context: Context, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}
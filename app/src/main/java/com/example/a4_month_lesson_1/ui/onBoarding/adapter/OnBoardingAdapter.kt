package com.example.a4_month_lesson_1.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a4_month_lesson_1.R
import com.example.a4_month_lesson_1.databinding.ItemOnboardingBinding
import com.example.a4_month_lesson_1.model.OnBoard

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val image = arrayListOf(R.raw.man,R.raw.update,R.raw.goodbye)
    private val data = arrayListOf(
        OnBoard(
            "Добро пожаловать",
            "Добро пожаловать в наше приложение, в нашем приложении вы сможете написать и сохранить любую информацию.",

        ),
        OnBoard(
            "Обновление",
            "На данный момент наше приложение в стадии разроботки поэтому ожидайте новых обновлений.",

        ),
        OnBoard(
            "До скорой встречи",
            "Надеюсь у вас не возникнет никаких проблем при использовании нашего приложения.",

        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.titleTv.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.lottieMain.setAnimation(image[position])
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }

    }
}
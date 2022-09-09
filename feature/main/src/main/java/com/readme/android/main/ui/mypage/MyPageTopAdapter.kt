package com.readme.android.main.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readme.android.core_ui.ext.navigateActivity
import com.readme.android.domain.entity.MyPageUser
import com.readme.android.main.databinding.LayoutMyPageTopBinding

class MyPageTopAdapter : RecyclerView.Adapter<MyPageTopAdapter.MyPageTopViewHolder>() {
    var myPageUser: MyPageUser? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageTopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutMyPageTopBinding.inflate(inflater, parent, false)
        return MyPageTopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageTopViewHolder, position: Int) {
        holder.onBind(myPageUser)
    }

    override fun getItemCount() = 1

    class MyPageTopViewHolder(
        private val binding: LayoutMyPageTopBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myPageUser: MyPageUser?) {
            myPageUser?.let {
                binding.userData = it

                binding.btnSetting.setOnClickListener {
                    with(itemView.context) {
                        navigateActivity<MyPageSettingActivity>()
                    }
                }
            }
        }
    }
}

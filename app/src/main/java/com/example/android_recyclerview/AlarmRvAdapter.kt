package com.example.android_recyclerview

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android_recyclerview.databinding.ItemAlarmBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AlarmRvAdapter(context: Context) : RecyclerView.Adapter<AlarmRvAdapter.AlarmRvViewHolder>() {

    private var itemList = mutableListOf<ItemAlarm>()

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmRvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlarmBinding.inflate(layoutInflater, parent, false)
        return AlarmRvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmRvViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(list : MutableList<ItemAlarm>) {
        itemList = list
    }

    inner class AlarmRvViewHolder(val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemAlarm) {
            binding.tvAlarmListAm.text = item.day
            binding.tvAlarmListTime.text = item.time
            binding.swAlarmListCheck.isChecked = item.alarmCheck

            binding.ivAlarmListMore.setOnClickListener {
                val right = PopupMenu(mContext, it)
                right.menuInflater.inflate(R.menu.right_menu, right.menu)

                right.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.delete -> {
                            deleteList(adapterPosition)
                        }
                    }
                    true
                }
                right.show()
            }
            binding.itemMain.setOnClickListener {
                val cal = Calendar.getInstance()

                val timeSet = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)

                    fun day(): String {
                        return if (hour > 12) "오후"
                        else "오전"
                    }

                    val time = SimpleDateFormat("HH:mm").format(cal.time)
                    itemList[adapterPosition].time = time
                    savePre("key", itemList as ArrayList<ItemAlarm>)
                    notifyDataSetChanged()
                }
                TimePickerDialog(
                    mContext, android.app.AlertDialog.THEME_HOLO_LIGHT, timeSet, cal.get(
                        Calendar.HOUR_OF_DAY
                    ), cal.get(Calendar.MINUTE), true
                ).show()
            }
            binding.swAlarmListCheck.setOnClickListener {

            }

        }
    }

    fun deleteList(position: Int){
        itemList.removeAt(position)
        savePre("Alarm", itemList as ArrayList<ItemAlarm>)
        notifyDataSetChanged()
    }

    private fun savePre(key: String, list: ArrayList<ItemAlarm>) {
        val sp = mContext.getSharedPreferences("timeData", Context.MODE_PRIVATE)
        val editor = sp.edit()
        var gson = Gson()
        var json = gson.toJson(list)
        editor.putString("Alarm", json)
        editor.apply()
    }

    private fun loadPre(key: String): ArrayList<ItemAlarm> {
        val sp = mContext.getSharedPreferences("timeData", Context.MODE_PRIVATE)
        var gson = Gson()
        var json: String = sp.getString("Alarm", "") ?: ""
        val type = object : TypeToken<ArrayList<ItemAlarm>>() {}.type
        return gson.fromJson(json, type)
    }

}
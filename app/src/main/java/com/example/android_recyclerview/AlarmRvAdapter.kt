package com.example.android_recyclerview

import android.annotation.SuppressLint
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.MotionEvent
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

class AlarmRvAdapter(context: Context) : RecyclerView.Adapter<AlarmRvAdapter.AlarmRvViewHolder>(), ItemTouchHelperCallback.OnItemMoveListener {

    private var itemList = mutableListOf<ItemAlarm>()

    val mContext = context

    private lateinit var dragListener: OnStartDragListener

    private val checkboxStatus = SparseBooleanArray()

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

    inner class AlarmRvViewHolder(private val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: ItemAlarm) {
            binding.tvAlarmListAm.text = item.day
            binding.tvAlarmListTime.text = item.time
            binding.swAlarmListCheck.isChecked = checkboxStatus[adapterPosition]

            
            // 자리이동 구현
            binding.itemMain.setOnTouchListener { view, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this)
                }
                return@setOnTouchListener false
            }

            // 체크박스 유지
            binding.swAlarmListCheck.setOnClickListener {
                if (!binding.swAlarmListCheck.isChecked){
                    checkboxStatus.put(adapterPosition, false)
                } else {
                    checkboxStatus.put(adapterPosition, true)
                    notifyItemChanged(adapterPosition)
                }
            }

            // 삭제
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

            // 수정
            binding.itemMain.setOnClickListener {
                val cal = Calendar.getInstance()

                val timeSet = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)

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

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }


}
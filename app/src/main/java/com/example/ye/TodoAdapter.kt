package com.example.ye

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.first.view.*


class TodoAdapter(
    private val todos: MutableList<ToDoClass>
) : RecyclerView.Adapter< TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.first,
                parent,
                false
    )
)
    }
    fun addTodo(todo: ToDoClass){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun removeTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo= todos[position]
            holder.itemView.apply {
                tvToDoTitle.text = currentTodo.title
                cbDone.isChecked = currentTodo.isChecked
                toggleStrikeThrough(tvToDoTitle,currentTodo.isChecked)
                cbDone.setOnCheckedChangeListener{_,isChecked ->
                    toggleStrikeThrough(tvToDoTitle, isChecked)
                    currentTodo.isChecked = !currentTodo.isChecked
                }
            }
    }
    private fun toggleStrikeThrough(tvToDoTitle: TextView,isChecked: Boolean){
        if(isChecked){
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return todos.size

    }

}
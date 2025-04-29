package com.panevrn.kumantsovtodolist.ui.tasklist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.panevrn.kumantsovtodolist.databinding.DialogAddTaskBinding
import com.panevrn.kumantsovtodolist.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(LayoutInflater.from(requireContext()))
        AlertDialog.Builder(requireContext()).setTitle("Новая задача")
            .setView(dialogBinding.root)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = dialogBinding.etTitle.text.toString().trim()
                val description = dialogBinding.etDescription.text.toString().trim()

                if (title.isNotEmpty()) {
                    Log.i("My Tag", "It's working")
                    // TODO: Передаем в ViewModel
                }
                else {
                    Toast.makeText(requireContext(), "Название задачи не должно быть пустым!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Отмена") { _, _, ->
                Toast.makeText(requireContext(), "Создание задачи было отменено", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
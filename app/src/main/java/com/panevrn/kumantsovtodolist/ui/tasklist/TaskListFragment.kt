package com.panevrn.kumantsovtodolist.ui.tasklist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.panevrn.domain.usecase.addtask.AddTaskRequest
import com.panevrn.kumantsovtodolist.databinding.DialogAddTaskBinding
import com.panevrn.kumantsovtodolist.databinding.FragmentTaskListBinding
import com.panevrn.kumantsovtodolist.viewmodel.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.panevrn.kumantsovtodolist.viewmodel.AddTaskUiEvent
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by viewModels()
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
        collectUiEvents()

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
        AlertDialog.Builder(requireContext())
            .setTitle("Новая задача")
            .setView(dialogBinding.root)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = dialogBinding.etTitle.text.toString().trim()
                val description = dialogBinding.etDescription.text.toString().trim()

                if (title.isNotEmpty()) {
                    Log.i("My Tag", "Отправка запроса на добавление задачи")
                    viewModel.onAddTask(
                        AddTaskRequest(title = title, description = description)
                    )
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


    private fun collectUiEvents() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is AddTaskUiEvent.ShowToast -> {
                            Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        }

                        is AddTaskUiEvent.CloseDialog -> TODO()
                    }
                }
            }
        }
    }


}
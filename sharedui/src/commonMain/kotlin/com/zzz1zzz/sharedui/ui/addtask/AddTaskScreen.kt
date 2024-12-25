package com.zzz1zzz.sharedui.ui.addtask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.zzz1zzz.sharedui.ui.TaskAddEditForm
import com.zzz1zzz.todolist.viewModel.AddTaskViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import todolist.sharedui.generated.resources.Res
import todolist.sharedui.generated.resources.screen_name_add_task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskRoute(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.screen_name_add_task))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        AddTaskScreen(
            modifier = Modifier.padding(contentPadding),
            navigateBack = {
                navigateBack()
            },
        )
    }
}


@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = { },
) {
    val viewModel: AddTaskViewModel = koinViewModel()

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = modifier
    ) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf<String>("") }

        TaskAddEditForm(
            modifier = Modifier.padding(8.dp),
            title = title,
            onTitleChanged = { title = it },
            description = description,
            onDescriptionChanged = { description = it },
            onSaveClick = {
                viewModel.addTask(title, description)
                navigateBack()
            },
            focusRequester = focusRequester,
        )
    }

}

@Preview
@Composable
private fun AddTaskScreenPreview() {
    AddTaskScreen()
}
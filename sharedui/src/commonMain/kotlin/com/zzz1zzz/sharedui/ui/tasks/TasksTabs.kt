package com.zzz1zzz.sharedui.ui.tasks

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import todolist.sharedui.generated.resources.Res
import todolist.sharedui.generated.resources.label_active
import todolist.sharedui.generated.resources.label_completed


@Composable
fun TasksTabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        selectedTabIndex = pagerState.currentPage,
    ) {
        Tab(
            selected = 0 == pagerState.currentPage,
            text = { Text(text = stringResource(Res.string.label_active)) },
            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
        )
        Tab(
            selected = 1 == pagerState.currentPage,
            text = { Text(text = stringResource(Res.string.label_completed)) },
            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
        )
    }
}
package com.zzz1zzz.sharedui.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun BottomNavigationBar(
    selectedPageId: Int,
    onPageSelected: (Int) -> Unit,
) {
    NavigationBar {
        BottomNavigationItems.entries.forEach { item ->
            val isSelected = selectedPageId == item.itemId
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            resource = if (isSelected) item.iconSelected else item.icon
                        ),
                        contentDescription = stringResource(resource = item.resourceId)
                    )
                },
                label = { Text(text = stringResource(resource = item.resourceId)) },
                selected = isSelected,
                onClick = { onPageSelected(item.itemId) }
            )
        }
    }
}
package com.codelabs.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }


//핵심 사항: 상태를 끌어올릴 때 상태의 이동 위치를 쉽게 파악할 수 있는 세 가지 규칙이 있습니다.
//
//상태는 적어도 그 상태를 사용하는 모든 컴포저블의 가장 낮은 공통 상위 요소로 끌어올려야 합니다(읽기).
//상태는 최소한 변경될 수 있는 가장 높은 수준으로 끌어올려야 합니다(쓰기).
//두 상태가 동일한 이벤트에 대한 응답으로 변경되는 경우 두 상태는 동일한 수준으로 끌어올려야 합니다.
//이러한 규칙에서 요구하는 것보다 더 높은 수준으로 상태를 끌어올릴 수 있습니다. 하지만 상태를 충분히 높은 수준으로 끌어올리지 않으면 단방향 데이터 흐름을 따르기가 어렵거나 불가능할 수 있습니다.
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuntizbe.kz.R
import com.kuntizbe.kz.screens.cities.AllCities
import com.kuntizbe.kz.screens.cities.CitiesViewModel
import com.kuntizbe.kz.screens.cities.SavedCities
import com.kuntizbe.kz.ui.theme.GraySettings
import com.kuntizbe.kz.ui.theme.Main
import com.kuntizbe.kz.ui.theme.White


@Composable
fun CustomTabs(viewModel: CitiesViewModel, context: Context, navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    val list = listOf(stringResource(id = R.string.mine_cities), stringResource(id = R.string.all_cities))

    Column {

        TabRow(selectedTabIndex = selectedIndex,
            backgroundColor = GraySettings,
            modifier = Modifier
                .shadow(elevation =1.dp),
            indicator = { tabPositions: List<TabPosition> ->
                Box {}
            }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = if (selected) Modifier
                        .background(
                            Main
                        )
                    else Modifier
                        .background(
                            White
                        ),
                    selected = selected,
                    onClick = { selectedIndex = index },
                    text = { Text(text = text, color = if(selected) White else Color.Black) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedIndex) {
            0 -> SavedCities(navController, context, viewModel)
            1 -> AllCities(viewModel, context, navController)
        }
    }
}
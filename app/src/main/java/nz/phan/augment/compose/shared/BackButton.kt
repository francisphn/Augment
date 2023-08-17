package nz.phan.augment.compose.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nz.phan.augment.R
import nz.phan.augment.ui.theme.Typography

@Composable
fun BackButton(backAction: () -> Unit) {
    Row(
        Modifier
            .padding(top = 30.dp)
            .padding(vertical = 20.dp)
            .wrapContentSize()
            .noRippleClickable { backAction.invoke() }) {
        Icon(
            Icons.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.back_icon_alt_text)
        )

        Text(
            text = stringResource(R.string.back_button_label),
            style = Typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        )
    }
}
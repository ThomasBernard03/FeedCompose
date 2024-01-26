package fr.thomasbernard03.feed.presentation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.presentation.theme.FeedTheme

@Composable
fun TextField(
    text: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    placeholder: String = "",
    @DrawableRes trailingIcon : Int? = null,
    @DrawableRes leadingIcon : Int? = null,
    trailingText : String = "",
    isPassword : Boolean = false,
    readOnly : Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLength : Int = -1){

    androidx.compose.material3.TextField(
        value = TextFieldValue(text, selection = TextRange(text.length)),
        onValueChange = {
            if (maxLength == -1 || it.text.length <= maxLength){
                onTextChange(it.text)
            }
        },
        label = { Text(placeholder, style = MaterialTheme.typography.bodyMedium) },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .border(width = 1.dp, color = DarkGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = DarkGray,
            disabledLabelColor = DarkGray,
            unfocusedLabelColor = DarkGray
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (trailingIcon != null){
                Icon(
                    painter = painterResource(trailingIcon),
                    contentDescription = "Text field icon",
                    tint = Color.LightGray)
            }
            else if (trailingText.isNotEmpty()){
                Text(
                    text = trailingText,
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkGray,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        },
        leadingIcon = {
            if (leadingIcon != null){
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = "Text field icon",
                    tint = Color.LightGray)
            }
        },
        readOnly = readOnly,
        enabled = !readOnly
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextFieldPreview() {
    FeedTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                TextField(text = "06 82 28 91 81", onTextChange = {}, placeholder = "Phone")
                TextField(text = "", onTextChange = {}, placeholder = "Search")
                TextField(text = "", onTextChange = {}, placeholder = "Search", trailingIcon = R.drawable.star)
                TextField(text = "", onTextChange = {}, placeholder = "Search", trailingIcon = R.drawable.add, readOnly = true)
            }
        }
    }
}
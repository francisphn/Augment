package nz.phan.augment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import nz.phan.augment.R

val ibmPlexSansFamily = FontFamily(
    Font(R.font.plexsans_extralight, FontWeight.ExtraLight),
    Font(R.font.plexsans_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),

    Font(R.font.plexsans_light, FontWeight.Light),
    Font(R.font.plexsans_lightitalic, FontWeight.Light, FontStyle.Italic),

    Font(R.font.plexsans_regular, FontWeight.Normal),
    Font(R.font.plexsans_italic, FontWeight.Normal, FontStyle.Italic),

    Font(R.font.plexsans_medium, FontWeight.Medium),
    Font(R.font.plexsans_mediumitalic, FontWeight.Medium, FontStyle.Italic),

    Font(R.font.plexsans_semibold, FontWeight.SemiBold),
    Font(R.font.plexsans_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),

    Font(R.font.plexsans_bold, FontWeight.Bold),
    Font(R.font.plexsans_bolditalic, FontWeight.Bold, FontStyle.Italic),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    titleSmall = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.sp
    ),

    displayLarge = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),

    labelMedium = TextStyle(
        color = Color.DarkGray,
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    /* Other default text styles to override
    labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
    )
    */
)
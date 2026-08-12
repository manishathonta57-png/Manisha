package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        RewardsStoreScreen()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsStoreScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "QuestEarn",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    Text(
                        "2,450 XP",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                ),
                windowInsets = WindowInsets.statusBars
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                windowInsets = WindowInsets.navigationBars
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
                    label = { Text("Home", style = MaterialTheme.typography.labelMedium) },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Dashboard, contentDescription = "Dashboard") },
                    label = { Text("Dashboard", style = MaterialTheme.typography.labelMedium) },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Assignment, contentDescription = "Tasks") },
                    label = { Text("Tasks", style = MaterialTheme.typography.labelMedium) },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.CardGiftcard, contentDescription = "Rewards") },
                    label = { Text("Rewards", style = MaterialTheme.typography.labelMedium) },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
                    label = { Text("Profile", style = MaterialTheme.typography.labelMedium) },
                    selected = false,
                    onClick = { }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Rewards Store",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Redeem your hard-earned XP for real-world value. Select a withdrawal method below to convert your balance.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            
            items(rewardsOptions) { option ->
                RewardCard(option)
            }
        }
    }
}

data class RewardOption(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val iconColor: Color,
    val tag: String,
    val tagColor: Color,
    val tagBgColor: Color,
    val minimumXp: String
)

val rewardsOptions = listOf(
    RewardOption(
        title = "PayPal Cash",
        description = "Direct transfer to your PayPal account. Usually processes within 24 hours.",
        icon = Icons.Filled.AccountBalance,
        iconColor = Color(0xFF60A5FA),
        tag = "Global",
        tagColor = Color(0xFFb8c3ff),
        tagBgColor = Color(0xFF2e5bff).copy(alpha = 0.2f),
        minimumXp = "5,000 XP"
    ),
    RewardOption(
        title = "UPI Transfer",
        description = "Instant transfer to any UPI ID. Zero transaction fees applied.",
        icon = Icons.Filled.Payments,
        iconColor = Color(0xFFC084FC),
        tag = "India",
        tagColor = Color(0xFFddb7ff),
        tagBgColor = Color(0xFF6f00be).copy(alpha = 0.2f),
        minimumXp = "2,500 XP"
    ),
    RewardOption(
        title = "Google Play",
        description = "Gift card code delivered instantly to your registered email address.",
        icon = Icons.Filled.PlayArrow,
        iconColor = Color(0xFF4ADE80),
        tag = "Digital",
        tagColor = Color(0xFFb8c3ff),
        tagBgColor = Color(0xFF2e5bff).copy(alpha = 0.2f),
        minimumXp = "1,000 XP"
    ),
    RewardOption(
        title = "Flipkart Voucher",
        description = "E-voucher for shopping on Flipkart. Valid for 12 months from issue.",
        icon = Icons.Filled.ShoppingCart,
        iconColor = Color(0xFFFACC15),
        tag = "Shopping",
        tagColor = Color(0xFFddb7ff),
        tagBgColor = Color(0xFF6f00be).copy(alpha = 0.2f),
        minimumXp = "5,000 XP"
    )
)

@Composable
fun RewardCard(option: RewardOption) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.4f)
        ),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = option.icon,
                        contentDescription = null,
                        tint = option.iconColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(option.tagBgColor)
                        .border(1.dp, option.tagBgColor.copy(alpha = 0.5f), CircleShape)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = option.tag.uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        color = option.tagColor
                    )
                }
            }
            
            Column {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "MINIMUM",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = option.minimumXp,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Redeem",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

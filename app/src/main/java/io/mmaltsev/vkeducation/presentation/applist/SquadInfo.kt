package io.mmaltsev.vkeducation.presentation.applist

import androidx.annotation.DrawableRes
import io.mmaltsev.vkeducation.R

// ---------- Данные ----------
data class SquadInfo(
    @DrawableRes val emblem: Int,
    val name: String,
    val direction_work: String,
    val descriptor: String
)

val squads = listOf(
    SquadInfo(R.drawable.ic_altair, "Альтаир", "ССервО", "Сервестные отряды выезжают на юга и работают в курортах в сфере гостеприимства. Их основной вид деятельности - это работа в ресторанах и отелях."),
    SquadInfo(R.drawable.ic_artifex, "Артифекс", "САО", "Археологические отряды выезжают на раскопки, где изучают историю."),
    SquadInfo(R.drawable.ic_django, "Джанго", "ССхО", "Сельхозотряды работают на плантациях, чаще всего на виноградниках."),
    SquadInfo(R.drawable.ic_legenda, "Легенда", "СПО", "Педагогические отряды работают в Лагерях вожатыми"),
    SquadInfo(R.drawable.ic_los, "Лось", "ССхО", "Сельхозотряды работают на плантациях, чаще всего на виноградниках."),
    SquadInfo(R.drawable.ic_plamya, "Пламя", "ССО", "Строительные отряды работают на строках"),
    SquadInfo(R.drawable.ic_provorniy, "Проворный", "СОП", "Студенческие отряды проводников работаю на железной дороге, где наматывают неодну сотню километров"),
    SquadInfo(R.drawable.ic_svarog, "Сварог", "СПрО", "Производственные отряды работают на заводах и фабриках"),
    SquadInfo(R.drawable.ic_orion, "Орион", "ССО", "Строительные отряды работают на строках")
)


package org.longevityintime.githubapi.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

object DateSerializer: KSerializer<Date> {
    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(Date::class.java.name, PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Date {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return dateFormat.parse(decoder.decodeString())!!
    }
    override fun serialize(encoder: Encoder, value: Date) {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return encoder.encodeString(dateFormat.format(value))
    }
}
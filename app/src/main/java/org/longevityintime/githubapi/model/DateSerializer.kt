package org.longevityintime.githubapi.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

object DateSerializer: KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(Date::class.java.name, PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Date {
        return Date(decoder.decodeLong())
    }
    override fun serialize(encoder: Encoder, value: Date) {
        return encoder.encodeLong(value.time)
    }
}
package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class BookWorkSerializer: KSerializer<BookWorkDto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        BookWorkDto::class.simpleName!!
    ) {
        element<String?>("description")
    }



    override fun deserialize(decoder: Decoder): BookWorkDto {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: BookWorkDto) {
        TODO("Not yet implemented")
    }
}
package com.anandbibek.web.notifications.domain.mapper

interface DataMapper <Model,Entity> {
    fun mapToModel(entity: Entity) : Model
    fun mapFromModel(model: Model) : Entity
}
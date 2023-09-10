package com.anandbibek.web.notifications.domain.mapper

import com.anandbibek.web.notifications.domain.model.Notice as NoticeModel
import com.anandbibek.web.notifications.persistence.entity.Notice as NoticeEntity

class NoticeMapper: DataMapper<NoticeModel, NoticeEntity> {

    override fun mapFromModel(model: NoticeModel): NoticeEntity {
        return NoticeEntity(
            uid = model.id,
            title = model.title,
            data = model.data,
            url = model.url,
            pageName = model.pageName,
            isStarred= model.isStarred,
            time= model.time
        )
    }

    override fun mapToModel(entity: NoticeEntity):  NoticeModel {
        return NoticeModel(
            id = entity.uid,
            title = entity.title,
            data = entity.data,
            url = entity.url,
            pageName = entity.pageName,
            isStarred= entity.isStarred,
            time= entity.time
        )
    }
}
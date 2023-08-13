package com.anandbibek.web.notifications.data.notices

import com.anandbibek.web.notifications.model.Site

/*@InstallIn(SingletonComponent::class)
@EntryPoint*/
interface NoticesRepositoryFactory {
    fun getNoticesRepository(site: Site): NoticesRepository
}

class NoticesRepositoryFactoryImpl(
    private val repoMap: HashMap<String, NoticesRepository>,
    private val defaultRepo: NoticesRepository
) : NoticesRepositoryFactory {

//    @Inject
//    lateinit var tpscRepo: TPSCRepo;
//
//    @Inject
//    lateinit var emptyRepo: EmptyRepo;

//    fun getTpsc() : TPSCRepo {
//        return tpscRepo;
//    }
//
//    fun getEmpty() : EmptyRepo {
//        return emptyRepo;
//    }


    override fun getNoticesRepository(site: Site): NoticesRepository {
        return repoMap[site.id] ?: defaultRepo;
    }
}
package com.anandbibek.web.notifications.data.parser

import com.anandbibek.web.notifications.domain.model.Page

/*@InstallIn(SingletonComponent::class)
@EntryPoint*/
interface ParserFactory {
    fun getParser(page: Page): ParserInterface
}

class ParserFactoryImpl(
    private val repoMap: HashMap<String, ParserInterface>,
    private val defaultRepo: ParserInterface
) : ParserFactory {

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


    override fun getParser(page: Page): ParserInterface {
        return repoMap["tpsc"] ?: defaultRepo;
    }
}
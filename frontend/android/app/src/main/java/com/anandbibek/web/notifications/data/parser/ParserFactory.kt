package com.anandbibek.web.notifications.data.parser

import com.anandbibek.web.notifications.model.Page

/*@InstallIn(SingletonComponent::class)
@EntryPoint*/
interface ParserFactory {
    fun getParser(page: Page): PageParser
}

class ParserFactoryImpl(
    private val repoMap: HashMap<String, PageParser>,
    private val defaultRepo: PageParser
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


    override fun getParser(page: Page): PageParser {
        return repoMap[page.parser] ?: defaultRepo;
    }
}
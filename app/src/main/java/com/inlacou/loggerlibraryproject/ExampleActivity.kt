package com.inlacou.loggerlibraryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.inlacou.inker.Inker
import timber.log.Timber

class ExampleActivity : AppCompatActivity() {

	companion object {
		const val sampleSize = 300000
		const val timesToRun = 20
	}

	/**
	 * https://www.worldometers.info/geography/alphabetical-list-of-countries/
	 */
	private val countries = CountryBuilder.buildAll("1 \tAfghanistan \t38,928,346 \t652,860 \t60\n" + "2 \tAlbania \t2,877,797 \t27,400 \t105\n" + "3 \tAlgeria \t43,851,044 \t2,381,740 \t18\n" + "4 \tAndorra \t77,265 \t470 \t164\n" + "5 \tAngola \t32,866,272 \t1,246,700 \t26\n" + "6 \tAntigua and Barbuda \t97,929 \t440 \t223\n" + "7 \tArgentina \t45,195,774 \t2,736,690 \t17\n" + "8 \tArmenia \t2,963,243 \t28,470 \t104\n" + "9 \tAustralia \t25,499,884 \t7,682,300 \t3\n" + "10 \tAustria \t9,006,398 \t82,409 \t109\n" + "11 \tAzerbaijan \t10,139,177 \t82,658 \t123\n" + "12 \tBahamas \t393,244 \t10,010 \t39\n" + "13 \tBahrain \t1,701,575 \t760 \t2,239\n" + "14 \tBangladesh \t164,689,383 \t130,170 \t1,265\n" + "15 \tBarbados \t287,375 \t430 \t668\n" + "16 \tBelarus \t9,449,323 \t202,910 \t47\n" + "17 \tBelgium \t11,589,623 \t30,280 \t383\n" + "18 \tBelize \t397,628 \t22,810 \t17\n" + "19 \tBenin \t12,123,200 \t112,760 \t108\n" + "20 \tBhutan \t771,608 \t38,117 \t20\n" + "21 \tBolivia \t11,673,021 \t1,083,300 \t11\n" + "22 \tBosnia and Herzegovina \t3,280,819 \t51,000 \t64\n" + "23 \tBotswana \t2,351,627 \t566,730 \t4\n" + "24 \tBrazil \t212,559,417 \t8,358,140 \t25\n" + "25 \tBrunei \t437,479 \t5,270 \t83\n" + "26 \tBulgaria \t6,948,445 \t108,560 \t64\n" + "27 \tBurkina Faso \t20,903,273 \t273,600 \t76\n" + "28 \tBurundi \t11,890,784 \t25,680 \t463\n" + "29 \tCôte d'Ivoire \t26,378,274 \t318,000 \t83\n" + "30 \tCabo Verde \t555,987 \t4,030 \t138\n" + "31 \tCambodia \t16,718,965 \t176,520 \t95\n" + "32 \tCameroon \t26,545,863 \t472,710 \t56\n" + "33 \tCanada \t37,742,154 \t9,093,510 \t4\n" + "34 \tCentral African Republic \t4,829,767 \t622,980 \t8\n" + "35 \tChad \t16,425,864 \t1,259,200 \t13\n" + "36 \tChile \t19,116,201 \t743,532 \t26\n" + "37 \tChina \t1,439,323,776 \t9,388,211 \t153\n" + "38 \tColombia \t50,882,891 \t1,109,500 \t46\n" + "39 \tComoros \t869,601 \t1,861 \t467\n" + "40 \tCongo (Congo-Brazzaville) \t5,518,087 \t341,500 \t16\n" + "41 \tCosta Rica \t5,094,118 \t51,060 \t100\n" + "42 \tCroatia \t4,105,267 \t55,960 \t73\n" + "43 \tCuba \t11,326,616 \t106,440 \t106\n" + "44 \tCyprus \t1,207,359 \t9,240 \t131\n" + "45 \tCzechia (Czech Republic) \t10,708,981 \t77,240 \t139\n" + "46 \tDemocratic Republic of the Congo \t89,561,403 \t2,267,050 \t40\n" + "47 \tDenmark \t5,792,202 \t42,430 \t137\n" + "48 \tDjibouti \t988,000 \t23,180 \t43\n" + "49 \tDominica \t71,986 \t750 \t96\n" + "50 \tDominican Republic \t10,847,910 \t48,320 \t225\n" + "51 \tEcuador \t17,643,054 \t248,360 \t71\n" + "52 \tEgypt \t102,334,404 \t995,450 \t103\n" + "53 \tEl Salvador \t6,486,205 \t20,720 \t313\n" + "54 \tEquatorial Guinea \t1,402,985 \t28,050 \t50\n" + "55 \tEritrea \t3,546,421 \t101,000 \t35\n" + "56 \tEstonia \t1,326,535 \t42,390 \t31\n" + "57 \tEswatini (fmr. \"Swaziland\") \t1,160,164 \t17,200 \t67\n" + "58 \tEthiopia \t114,963,588 \t1,000,000 \t115\n" + "59 \tFiji \t896,445 \t18,270 \t49\n" + "60 \tFinland \t5,540,720 \t303,890 \t18\n" + "61 \tFrance \t65,273,511 \t547,557 \t119\n" + "62 \tGabon \t2,225,734 \t257,670 \t9\n" + "63 \tGambia \t2,416,668 \t10,120 \t239\n" + "64 \tGeorgia \t3,989,167 \t69,490 \t57\n" + "65 \tGermany \t83,783,942 \t348,560 \t240\n" + "66 \tGhana \t31,072,940 \t227,540 \t137\n" + "67 \tGreece \t10,423,054 \t128,900 \t81\n" + "68 \tGrenada \t112,523 \t340 \t331\n" + "69 \tGuatemala \t17,915,568 \t107,160 \t167\n" + "70 \tGuinea \t13,132,795 \t245,720 \t53\n" + "71 \tGuinea-Bissau \t1,968,001 \t28,120 \t70\n" + "72 \tGuyana \t786,552 \t196,850 \t4\n" + "73 \tHaiti \t11,402,528 \t27,560 \t414\n" + "74 \tHoly See \t801 \t0 \t2,003\n" + "75 \tHonduras \t9,904,607 \t111,890 \t89\n" + "76 \tHungary \t9,660,351 \t90,530 \t107\n" + "77 \tIceland \t341,243 \t100,250 \t3\n" + "78 \tIndia \t1,380,004,385 \t2,973,190 \t464\n" + "79 \tIndonesia \t273,523,615 \t1,811,570 \t151\n" + "80 \tIran \t83,992,949 \t1,628,550 \t52\n" + "81 \tIraq \t40,222,493 \t434,320 \t93\n" + "82 \tIreland \t4,937,786 \t68,890 \t72\n" + "83 \tIsrael \t8,655,535 \t21,640 \t400\n" + "84 \tItaly \t60,461,826 \t294,140 \t206\n" + "85 \tJamaica \t2,961,167 \t10,830 \t273\n" + "86 \tJapan \t126,476,461 \t364,555 \t347\n" + "87 \tJordan \t10,203,134 \t88,780 \t115\n" + "88 \tKazakhstan \t18,776,707 \t2,699,700 \t7\n" + "89 \tKenya \t53,771,296 \t569,140 \t94\n" + "90 \tKiribati \t119,449 \t810 \t147\n" + "91 \tKuwait \t4,270,571 \t17,820 \t240\n" + "92 \tKyrgyzstan \t6,524,195 \t191,800 \t34\n" + "93 \tLaos \t7,275,560 \t230,800 \t32\n" + "94 \tLatvia \t1,886,198 \t62,200 \t30\n" + "95 \tLebanon \t6,825,445 \t10,230 \t667\n" + "96 \tLesotho \t2,142,249 \t30,360 \t71\n" + "97 \tLiberia \t5,057,681 \t96,320 \t53\n" + "98 \tLibya \t6,871,292 \t1,759,540 \t4\n" + "99 \tLiechtenstein \t38,128 \t160 \t238\n" + "100 \tLithuania \t2,722,289 \t62,674 \t43\n" + "101 \tLuxembourg \t625,978 \t2,590 \t242\n" + "102 \tMadagascar \t27,691,018 \t581,795 \t48\n" + "103 \tMalawi \t19,129,952 \t94,280 \t203\n" + "104 \tMalaysia \t32,365,999 \t328,550 \t99\n" + "105 \tMaldives \t540,544 \t300 \t1,802\n" + "106 \tMali \t20,250,833 \t1,220,190 \t17\n" + "107 \tMalta \t441,543 \t320 \t1,380\n" + "108 \tMarshall Islands \t59,190 \t180 \t329\n" + "109 \tMauritania \t4,649,658 \t1,030,700 \t5\n" + "110 \tMauritius \t1,271,768 \t2,030 \t626\n" + "111 \tMexico \t128,932,753 \t1,943,950 \t66\n" + "112 \tMicronesia \t548,914 \t700 \t784\n" + "113 \tMoldova \t4,033,963 \t32,850 \t123\n" + "114 \tMonaco \t39,242 \t1 \t26,337\n" + "115 \tMongolia \t3,278,290 \t1,553,560 \t2\n" + "116 \tMontenegro \t628,066 \t13,450 \t47\n" + "117 \tMorocco \t36,910,560 \t446,300 \t83\n" + "118 \tMozambique \t31,255,435 \t786,380 \t40\n" + "119 \tMyanmar (formerly Burma) \t54,409,800 \t653,290 \t83\n" + "120 \tNamibia \t2,540,905 \t823,290 \t3\n" + "121 \tNauru \t10,824 \t20 \t541\n" + "122 \tNepal \t29,136,808 \t143,350 \t203\n" + "123 \tNetherlands \t17,134,872 \t33,720 \t508\n" + "124 \tNew Zealand \t4,822,233 \t263,310 \t18\n" + "125 \tNicaragua \t6,624,554 \t120,340 \t55\n" + "126 \tNiger \t24,206,644 \t1,266,700 \t19\n" + "127 \tNigeria \t206,139,589 \t910,770 \t226\n" + "128 \tNorth Korea \t25,778,816 \t120,410 \t214\n" + "129 \tNorth Macedonia \t2,083,374 \t25,220 \t83\n" + "130 \tNorway \t5,421,241 \t365,268 \t15\n" + "131 \tOman \t5,106,626 \t309,500 \t16\n" + "132 \tPakistan \t220,892,340 \t770,880 \t287\n" + "133 \tPalau \t18,094 \t460 \t39\n" + "134 \tPalestine State \t5,101,414 \t6,020 \t847\n" + "135 \tPanama \t4,314,767 \t74,340 \t58\n" + "136 \tPapua New Guinea \t8,947,024 \t452,860 \t20\n" + "137 \tParaguay \t7,132,538 \t397,300 \t18\n" + "138 \tPeru \t32,971,854 \t1,280,000 \t26\n" + "139 \tPhilippines \t109,581,078 \t298,170 \t368\n" + "140 \tPoland \t37,846,611 \t306,230 \t124\n" + "141 \tPortugal \t10,196,709 \t91,590 \t111\n" + "142 \tQatar \t2,881,053 \t11,610 \t248\n" + "143 \tRomania \t19,237,691 \t230,170 \t84\n" + "144 \tRussia \t145,934,462 \t16,376,870 \t9\n" + "145 \tRwanda \t12,952,218 \t24,670 \t525\n" + "146 \tSaint Kitts and Nevis \t53,199 \t260 \t205\n" + "147 \tSaint Lucia \t183,627 \t610 \t301\n" + "148 \tSaint Vincent and the Grenadines \t110,940 \t390 \t284\n" + "149 \tSamoa \t198,414 \t2,830 \t70\n" + "150 \tSan Marino \t33,931 \t60 \t566\n" + "151 \tSao Tome and Principe \t219,159 \t960 \t228\n" + "152 \tSaudi Arabia \t34,813,871 \t2,149,690 \t16\n" + "153 \tSenegal \t16,743,927 \t192,530 \t87\n" + "154 \tSerbia \t8,737,371 \t87,460 \t100\n" + "155 \tSeychelles \t98,347 \t460 \t214\n" + "156 \tSierra Leone \t7,976,983 \t72,180 \t111\n" + "157 \tSingapore \t5,850,342 \t700 \t8,358\n" + "158 \tSlovakia \t5,459,642 \t48,088 \t114\n" + "159 \tSlovenia \t2,078,938 \t20,140 \t103\n" + "160 \tSolomon Islands \t686,884 \t27,990 \t25\n" + "161 \tSomalia \t15,893,222 \t627,340 \t25\n" + "162 \tSouth Africa \t59,308,690 \t1,213,090 \t49\n" + "163 \tSouth Korea \t51,269,185 \t97,230 \t527\n" + "164 \tSouth Sudan \t11,193,725 \t610,952 \t18\n" + "165 \tSpain \t46,754,778 \t498,800 \t94\n" + "166 \tSri Lanka \t21,413,249 \t62,710 \t341\n" + "167 \tSudan \t43,849,260 \t1,765,048 \t25\n" + "168 \tSuriname \t586,632 \t156,000 \t4\n" + "169 \tSweden \t10,099,265 \t410,340 \t25\n" + "170 \tSwitzerland \t8,654,622 \t39,516 \t219\n" + "171 \tSyria \t17,500,658 \t183,630 \t95\n" + "172 \tTajikistan \t9,537,645 \t139,960 \t68\n" + "173 \tTanzania \t59,734,218 \t885,800 \t67\n" + "174 \tThailand \t69,799,978 \t510,890 \t137\n" + "175 \tTimor-Leste \t1,318,445 \t14,870 \t89\n" + "176 \tTogo \t8,278,724 \t54,390 \t152\n" + "177 \tTonga \t105,695 \t720 \t147\n" + "178 \tTrinidad and Tobago \t1,399,488 \t5,130 \t273\n" + "179 \tTunisia \t11,818,619 \t155,360 \t76\n" + "180 \tTurkey \t84,339,067 \t769,630 \t110\n" + "181 \tTurkmenistan \t6,031,200 \t469,930 \t13\n" + "182 \tTuvalu \t11,792 \t30 \t393\n" + "183 \tUganda \t45,741,007 \t199,810 \t229\n" + "184 \tUkraine \t43,733,762 \t579,320 \t75\n" + "185 \tUnited Arab Emirates \t9,890,402 \t83,600 \t118\n" + "186 \tUnited Kingdom \t67,886,011 \t241,930 \t281\n" + "187 \tUnited States of America \t331,002,651 \t9,147,420 \t36\n" + "188 \tUruguay \t3,473,730 \t175,020 \t20\n" + "189 \tUzbekistan \t33,469,203 \t425,400 \t79\n" + "190 \tVanuatu \t307,145 \t12,190 \t25\n" + "191 \tVenezuela \t28,435,940 \t882,050 \t32\n" + "192 \tVietnam \t97,338,579 \t310,070 \t314\n" + "193 \tYemen \t29,825,964 \t527,970 \t56\n" + "194 \tZambia \t18,383,955 \t743,390 \t25\n" + "195 \tZimbabwe \t14,862,924 \t386,850 \t38")
	/**
	 * Source: https://en.wikipedia.org/wiki/List_of_cities_in_the_European_Union_by_population_within_city_limits
	 */
	private val cities = CityBuilder.buildAll("1 \tBerlin \tGermany \t3,664,088 \t31 December 2020 \t[1] \tBerlin Skyline Fernsehturm 02.jpg\n" + "2 \tMadrid \tSpain \t3,305,408 \t1 January 2021 \t[2] \tMadrid - Sky Bar 360º (Hotel Riu Plaza España), vistas 19.jpg\n" + "3 \tRome \tItaly \t2,770,226 \t1 January 2021 \t[3] \tVictor Emanuel II Rome 2011 11.jpg\n" + "4 \tParis \tFrance \t2,165,423 \t1 January 2019 \t[4] \tParis vue d'ensemble tour Eiffel.jpg\n" + "5 \tBucharest \tRomania \t2,161,347 \t1 July 2021 \t[5] \tBucharest city center.jpg\n" + "6 \tVienna \tAustria \t1,931,830 \t1 January 2022 \t[6] \tStephansdom Vienna July 2008 (27)-Stephansdom Vienna July 2008 (31).jpg\n" + "7 \tHamburg \tGermany \t1,852,478 \t31 December 2020 \t[1] \tHamburg Rathaus.jpg\n" + "8 \tWarsaw \tPoland \t1,792,718 \t30 June 2021 \t[7] \tWarsaw (19130300421).jpg\n" + "9 \tBudapest \tHungary \t1,723,836 \t1 January 2021 \t[8] \tSzéchenyi Chain Bridge in Budapest at night.jpg\n" + "10 \tBarcelona \tSpain \t1,636,732 \t1 January 2021 \t[2] \tBarcelona from Sagrada Familia (2) (cropped).JPG\n" + "11 \tMunich \tGermany \t1,488,202 \t31 December 2020 \t[1] \tStadtbild München.jpg\n" + "12 \tMilan \tItaly \t1,374,582 \t1 January 2021 \t[3] \tMilan skyline skyscrapers of Porta Nuova business district.jpg\n" + "13 \tPrague \tCzech Republic \t1,335,084 \t1 January 2021 \t[9] \tNight view of the Castle and Charles Bridge, Prague - 8034.jpg\n" + "14 \tSofia \tBulgaria \t1,308,412 \t31 December 2020 \t[10] \tParty House by night, Sofia (by Pudelek, cropped by ArionEstar).JPG\n" + "15 \tCologne \tGermany \t1,083,498 \t31 December 2020 \t[1] \tNightview of Cologne Cathedrale across the River Rhine.jpg\n" + "16 \tStockholm \tSweden \t975,551 \t31 December 2020 \t[11] \tStadshus-Panorama 2.jpg\n" + "17 \tNaples \tItaly \t922,094 \t1 January 2021 \t[3] \tNapoli6.png\n" + "18 \tAmsterdam \tNetherlands \t883,487 \t31 December 2021 \t[12] \tAmsterdam (5086325695).jpg\n" + "19 \tMarseille \tFrance \t870,731 \t1 January 2019 \t[4] \tMarseille-la-nuit-by-F.Laffont-feraud.jpg\n" + "20 \tTurin \tItaly \t858,205 \t1 January 2021 \t[3] \tTurin monte cappuccini.jpg\n" + "21 \tZagreb \tCroatia \t808,785 \t31 December 2020 \t[13] \tZagreb (29255640143).jpg\n" + "22 \tValencia \tSpain \t789,744 \t1 January 2021 \t[2] \tLa ciutat de València des del Micalet.JPG\n" + "23 \tKraków \tPoland \t780,796 \t30 June 2021 \t[7] \tSukiennice and Main Market Square Krakow Poland.JPG\n" + "24 \tFrankfurt am Main \tGermany \t764,104 \t31 December 2020 \t[1] \tSkyline Frankfurt am Main 2015.jpg\n" + "25 \tSeville \tSpain \t684,234 \t1 January 2021 \t[2] \tSevilla - March 2011 - 059.jpg\n" + "26 \tZaragoza \tSpain \t675,301 \t1 January 2021 \t[2] \tPanorama of Zaragoza from the top of the cathedral (3847901093).jpg\n" + "27 \tŁódź \tPoland \t667,923 \t30 June 2021 \t[7] \t20180426-084611-lodz-Solidarność-Roundabout-Łódź.jpg\n" + "28 \tAthens \tGreece \t664,046 \t30 June 2011 \t[14] \tAtenes i el mont Licabet des de l'Acròpoli.JPG\n" + "29 \tHelsinki \tFinland \t658,864 \t31 December 2021 \t[15] \tSouthern Helsinki panorama 2011-06-28 1.jpg\n" + "30 \tRotterdam \tNetherlands \t655,418 \t31 December 2021 \t[12] \t206 Rotterdam bridge.jpg\n" + "31 \tWrocław \tPoland \t641,201 \t30 June 2021 \t[7] \tWrocław - Rynek 2015-12-25 12-44-18.JPG\n" + "32 \tCopenhagen \tDenmark \t638,117 \t1 January 2021 \t[16] \tCopenhagen Christiansborg IMG 5590.jpg\n" + "33 \tPalermo \tItaly \t637,885 \t1 January 2021 \t[3] \tPalermo 0454 2013.jpg\n" + "34 \tStuttgart \tGermany \t630,305 \t31 December 2020 \t[1] \tStuttgart - panoramio (40).jpg\n" + "35 \tDüsseldorf \tGermany \t620,523 \t31 December 2020 \t[1] \tDüsseldorf, Marina Düsseldorf.JPG\n" + "36 \tRiga \tLatvia \t614,618 \t1 January 2021 \t[17] \tRiga - Latvia.jpg\n" + "37 \tLeipzig \tGermany \t597,493 \t31 December 2020 \t[1] \tLeipzig Fockeberg Zentrum.jpg\n" + "38 \tDortmund \tGermany \t587,696 \t31 December 2020 \t[1] \tDortmund Panorama.jpg\n" + "39 \tGothenburg \tSweden \t583,056 \t31 December 2020 \t[11] \tGothenburgbynight.jpg\n" + "40 \tEssen \tGermany \t582,415 \t31 December 2020 \t[1] \tEssen-Panorama.jpg\n" + "41 \tMálaga \tSpain \t577,405 \t1 January 2021 \t[2] \tDa Gibralfaro.jpg\n" + "42 \tBremen \tGermany \t566,573 \t31 December 2020 \t[1] \tBremen aerial view 9.JPG\n" + "43 \tGenoa \tItaly \t566,410 \t1 January 2021 \t[3] \tTorre della Lanterna vista dal moderno Centro Direzionale di Genova S. Benigno ed elicoidale.jpg\n" + "44 \tVilnius \tLithuania \t560,120 \t1 May 2022 \t[18] \tVilnius city.jpg\n" + "45 \tDresden \tGermany \t556,227 \t31 December 2020 \t[1] \tDresden Altstadt (5549941423).jpg\n" + "46 \tDublin \tIreland \t554,554 \t24 April 2016 \t[19] \tTHE SAMUEL BECKETT BRIDGE 11 JANUARY 2018 (STILL TRYING FOR A UNIQUE IMAGE OF THIS BRIDGE)-135491 (39626174772).jpg\n" + "47 \tThe Hague \tNetherlands \t553,277 \t31 December 2021 \t[12] \tCityscape of The Hague, viewed from Het Plein (The Square).jpg\n" + "48 \tHanover \tGermany \t534,049 \t31 December 2020 \t[1] \tLuftbild Hannover Rathaus.JPG\n" + "49 \tPoznań \tPoland \t530,464 \t30 June 2021 \t[7] \tAyuntamiento, Poznan, Polonia, 2014-09-18, DD 73-75 HDR.jpg\n" + "50 \tAntwerp \tBelgium \t528,903 \t1 January 2022 \t[20] \tGrote Markt and Cathedral of Our Lady - Antwerp, Belgium - panoramio.jpg\n" + "51 \tLyon \tFrance \t522,969 \t1 January 2019 \t[4] \tLyon vue depuis fourviere.jpg\n" + "52 \tNuremberg \tGermany \t515,543 \t31 December 2020 \t[1] \tNuremberg panorama morning 3.jpg\n" + "53 \tLisbon \tPortugal \t509,614 \t31 December 2020 \t[21] \tAerial view of Augusta Street, Lisbon (50644280948).jpg\n" + "54 \tDuisburg \tGermany \t495,885 \t31 December 2020 \t[1] \tWestlicher Innenhafen 26 05 12.JPG\n" + "55 \tToulouse \tFrance \t493,465 \t1 January 2019 \t[4] \tToulouse by night with Basilique Saint-Sernin.jpg\n" + "56 \tGdańsk \tPoland \t470,633 \t30 June 2021 \t[7] \tCalle Dlugie Pobrzeze, Gdansk, Polonia, 2013-05-20, DD 07.jpg\n" + "57 \tMurcia \tSpain \t460,349 \t1 January 2021 \t[2] \t(Murcia) Vista del Este de Murcia desde la Camapana de San José de la Catedral de Murcia (1) (5373232199) (cropped).jpg\n" + "58 \tBratislava \tSlovakia \t440,948 \t1 July 2020 \t[22] \tBratislava Panorama R01.jpg\n" + "59 \tTallinn \tEstonia \t438,341 \t1 January 2021 \t[23] \tTallinn sea.png\n" + "60 \tPalma de Mallorca \tSpain \t419,366 \t1 January 2021 \t[2] \tBellver Castle 2008 Palma Mallorca 130.JPG\n" + "61 \tSzczecin \tPoland \t396,472 \t30 June 2021 \t[7] \tNocny widok z wyspy Grodzkiej, Szczecin - panoramio.jpg\n" + "62 \tSintra \tPortugal \t392,887 \t31 December 2020 \t[21] \tCabo da Roca & Sintra Sintra (3179815244).jpg\n" + "63 \tBologna \tItaly \t391,686 \t1 January 2021 \t[3] \tSkyline of Bologna against the sunset.jpg\n" + "64 \tIași \tRomania \t391,024 \t1 July 2021 \t[5] \tPalatculturaiasi.jpg\n" + "65 \tBrno \tCzech Republic \t382,405 \t1 January 2021 \t[9] \tBrno mrakodrapy.JPG\n" + "66 \tLas Palmas \tSpain \t378,675 \t1 January 2021 \t[2] \tLas Palmas panorama.jpg\n" + "67 \tFlorence \tItaly \t368,419 \t1 January 2021 \t[3] \tSunset in Florence from the Piazza Michelangelo.jpg\n" + "68 \tBochum \tGermany \t364,454 \t31 December 2020 \t[1] \tBergbaumuseum Bochum Pano.jpg\n" + "69 \tUtrecht \tNetherlands \t361,966 \t31 December 2021 \t[12] \tPanorama Utrecht.jpg\n" + "70 \tWuppertal \tGermany \t355,004 \t31 December 2020 \t[1] \tWuppertal ansicht.jpg\n" + "71 \tAarhus \tDenmark \t352,751 \t1 January 2021 \t[16] \tAarhus havnefront 20170902.jpg\n" + "72 \tMalmö \tSweden \t347,949 \t31 December 2020 \t[11] \tCentral Malmö.JPG\n" + "73 \tBilbao \tSpain \t346,405 \t1 January 2021 \t[2] \tPanoramica bilbao.jpg\n" + "74 \tNice \tFrance \t342,669 \t1 January 2019 \t[4] \tAerial view downtown Nice.jpg\n" + "75 \tPlovdiv \tBulgaria \t342,048 \t31 December 2020 \t[10] \tAncient theatre plovdiv-3.jpg\n" + "76 \tBydgoszcz \tPoland \t341,692 \t30 June 2021 \t[7] \tBdg OperaNova noc 15 07-2013.jpg\n" + "77 \tVarna \tBulgaria \t341,516 \t31 December 2020 \t[10] \tVarna Eastern breakwater 1.JPG\n" + "78 \tLublin \tPoland \t337,778 \t30 June 2021 \t[7] \tLublin18395.jpg\n" + "79 \tAlicante \tSpain \t337,304 \t1 January 2021 \t[2] \tPuerto de Alicante desde el Castillo de Santa Bárbara.JPG\n" + "80 \tBielefeld \tGermany \t333,509 \t31 December 2020 \t[1] \tBielefeld City.jpg\n" + "81 \tBonn \tGermany \t330,579 \t31 December 2020 \t[1] \tBonn-center-2016-01.jpg\n" + "82 \tCluj-Napoca \tRomania \t328,316 \t1 July 2021 \t[5] \tBiserica Mihail.JPG\n" + "83 \tThessaloniki \tGreece \t325,182 \t30 June 2011 \t[14] \tPanorama solun.JPG\n" + "84 \tCórdoba \tSpain \t322,071 \t1 January 2021 \t[2] \tCórdoba vista aérea.jpg\n" + "85 \tNantes \tFrance \t318,808 \t1 January 2019 \t[4] \tPanorama depuis tour Dobree.jpg\n" + "86 \tTimișoara \tRomania \t318,296 \t1 July 2021 \t[5] \tFestivalul Inimilor Timișoara.jpg\n" + "87 \tBari \tItaly \t317,205 \t1 January 2021 \t[3] \tBari AltstadtPanorama.jpg\n" + "88 \tMünster \tGermany \t316,403 \t31 December 2020 \t[1] \tMünster (9277320495) (3).jpg\n" + "89 \tMannheim \tGermany \t309,721 \t31 December 2020 \t[1] \tMannheim NUB city2.jpg\n" + "90 \tKarlsruhe \tGermany \t308,436 \t31 December 2020 \t[1] \tKarlsruhe town centre air.jpg\n" + "91 \tConstanța \tRomania \t306,607 \t1 July 2021 \t[5] \tNational History Museum in Constanţa (27654665352).jpg\n" + "92 \tGalați \tRomania \t304,957 \t1 July 2021 \t[5] \tDunareaSiPalatulNavigatiei.jpg\n" + "93 \tCatania \tItaly \t300,356 \t1 January 2021 \t[3] \tCatania-Etna-Sicilia-Italy-Castielli CC0 HQ1.JPG\n" + "94 \tVila Nova de Gaia \tPortugal \t300,018 \t31 December 2020 \t[21]", countries)
	/**
	 * Source: random
	 */
	private val persons = PersonBuilder.buildAll(sampleSize, countries, cities)

	private val logOnDurations = mutableListOf<Long>()
	private val logOffDurations = mutableListOf<Long>()
	private val timberOnDurations = mutableListOf<Long>()
	private val timberOffDurations = mutableListOf<Long>()
	private val inkerAOnDurations = mutableListOf<Long>()
	private val inkerAOffDurations = mutableListOf<Long>()
	private val inkerBOnDurations = mutableListOf<Long>()
	private val inkerBOffDurations = mutableListOf<Long>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_example)

		var timeStamp: Long
		var timeStamp2: Long
		var duration: Long
		var run = 0

		findViewById<TextView>(R.id.textview).text = "working... $timesToRun times with $sampleSize sample size each"

		var text = ""
		val absoluteStartTimestamp = System.currentTimeMillis()
		text = "$text Log.d vs Timber vs Logger\n\n"
		do{
			text = "$text RUN #$run\n"

			Inker.log = true
			timeStamp = System.currentTimeMillis()
			persons.forEach { if(Inker.log) Log.d("MainActivity", it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			logOnDurations.add(duration)
			text = "$text\t Log.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"

			Inker.log = false
			timeStamp = System.currentTimeMillis()
			persons.forEach { if(Inker.log) Log.d("MainActivity", it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			logOffDurations.add(duration)
			text = "$text\t Log.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"

			val debugTree = Timber.DebugTree()
			Timber.plant(debugTree)
			timeStamp = System.currentTimeMillis()
			persons.forEach { Timber.d(it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			timberOnDurations.add(duration)
			text = "$text\t Timber.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"
			Timber.uproot(debugTree)

			val releaseTree = TimberReleaseTree()
			Timber.plant(releaseTree)
			timeStamp = System.currentTimeMillis()
			persons.forEach { Timber.d(it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			timberOffDurations.add(duration)
			text = "$text\t Timber.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"
			Timber.uproot(releaseTree)

			Inker.log = true
			timeStamp = System.currentTimeMillis()
			persons.forEach { Inker.d(it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			inkerAOnDurations.add(duration)
			text = "$text\t Inker.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"


			Inker.log = false
			timeStamp = System.currentTimeMillis()
			persons.forEach { Inker.d(it.toString()) }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			inkerAOffDurations.add(duration)
			text = "$text\t Inker.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"

			Inker.log = true
			timeStamp = System.currentTimeMillis()
			persons.forEach { Inker.d { it.toString() } }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			inkerBOnDurations.add(duration)
			text = "$text\t Inker.d{} (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"

			Inker.log = false
			timeStamp = System.currentTimeMillis()
			persons.forEach { Inker.d { it.toString() } }
			timeStamp2 = System.currentTimeMillis()
			duration = timeStamp2-timeStamp
			inkerBOffDurations.add(duration)
			text = "$text\t Inker.d{} (disabled):\t\t${duration.msToTime()} (${duration}ms)\n\n"
			run++
		}while (run<timesToRun)

		text = "$text Sample size: $sampleSize\n"
		text = "$text Number of runs: $timesToRun\n"
		text = "$text Total results:\n"
		duration = logOnDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Log.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = logOffDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Log.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = timberOnDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Timber.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = timberOffDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Timber.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = inkerAOnDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Inker.d() (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = inkerAOffDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Inker.d() (disabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = inkerBOnDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Inker.d{} (enabled):\t\t${duration.msToTime()} (${duration}ms)\n"
		duration = inkerBOffDurations.reduce { acc, l -> acc+l }/timesToRun
		text = "$text     - Average Inker.d{} (disabled):\t\t${duration.msToTime()} (${duration}ms)\n\n"

		text = "$text Code examples: \n"
		text = "$text     - Log.d() (enabled): if(true) Log.d()\n"
		text = "$text     - Log.d() (disabled): if(false) Log.d()\n"
		text = "$text     - Timber.d() (enabled): Timber.d() with Timber.DebugTree\n"
		text = "$text     - Timber.d() (disabled): Timber.d() with a Tree that overrides Timber.d to do nothing\n"
		text = "$text     - Inker.d() (enabled): Inker.d() with Inker.log to true\n"
		text = "$text     - Inker.d() (disabled): Inker.d() with Inker.log to false\n"
		text = "$text     - Inker.d{} (enabled): Inker.d{} with Inker.log to true\n"
		text = "$text     - Inker.d{} (disabled): Inker.d{} with Inker.log to false\n\n"

		text = "$text Total time: ${(System.currentTimeMillis()-absoluteStartTimestamp).msToTime()}\n"

		findViewById<TextView>(R.id.textview).text = text
	}

	data class Person(val name: String, val surname: String, val age: Int, val active: Boolean, val height: Double, val currentCity: City, val birthCountry: Country)
	data class City(val name: String, val country: Country?, val population: Double)
	data class Country(val name: String, val sizeM2: Double)

	object CountryBuilder {
		/**
		 * Example item '1 	Afghanistan 	38,928,346 	652,860 	60'
		 */
		fun buildAll(source: String): List<Country> = source.lines().map { it.split("\t").let { Country(it[1], it[3].replace(",", "").toDouble()) } }
	}
	object CityBuilder {
		/**
		 * Example item '1 	Berlin 	Germany 	3,664,088 	31 December 2020 	[1] 	Berlin Skyline Fernsehturm 02.jpg
		 */
		fun buildAll(source: String, countries: List<Country>): List<City> = source.lines().map { it.split("\t").let { City(it[1], countries.find { country -> country.name==it[2] }, it[3].replace(",", "").toDouble()) } }
	}
	object PersonBuilder {
		fun buildAll(number: Int, countries: List<Country>, cities: List<City>): List<Person>
		  = (0 .. number).map { Person(name = "", surname = "", age = (Math.random()*100).toInt(), active = Math.random()>0.5, height = Math.random()*1000, currentCity = cities.random(), birthCountry = countries.random()) }
	}
}
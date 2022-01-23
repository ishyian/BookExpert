package com.revolution.bookexpert.data

import com.revolution.bookexpert.R

object Library {
    val books = listOf(
        Book(
            title = "Перемоги про крастинацію! Як перестати відкладати справи на завтра.",
            author = "Людвіг П.",
            description = R.string.procrastination_book_1,
            image = "https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/9/4/94_1_41.jpg",
            type = BookType.PROCRASTINATION
        ),
        Book(
            title = "Зціли себе сам",
            author = "Луиза Хей",
            description = R.string.anxiety_book_1,
            image = "https://book-ye.com.ua/upload/medialibrary/432/Ztsili-sebe-sam.jpg",
            type = BookType.ANXIETY
        ),
        Book(
            title = "Психологія мотивації. Теорія і практика мотивування. Мотиваційний тренінг.",
            author = "Сергей Занюк",
            description = R.string.communication_book_1,
            image = "https://s.mind.ua/img/forall/books/1/17.jpg",
            type = BookType.COMMUNICATION
        ),
        Book(
            title = "Бути окей. Що важливо знати про психічне здоров’я ",
            author = "Дарка Озерна",
            description = R.string.self_care_book_1,
            image = "https://content1.rozetka.com.ua/goods/images/big/162265162.jpg",
            type = BookType.SELF_CARE
        ),
        Book(
            title = "Як хотіти й отримати все (але це неточно)",
            author = "Тетяна Лукинюк",
            description = R.string.work_life_book_1,
            image = "https://content1.rozetka.com.ua/goods/images/big/185944304.jpg",
            type = BookType.WORK_LIFE_BALANCE
        ),
        Book(
            title = "Короткий курс історії України",
            author = "Олександр Палій",
            description = R.string.other_book_1,
            image = "https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/i/m/img012_17_13.jpg",
            type = BookType.OTHER
        ),
        Book(
            title = "Атомні звички. Легкий і перевірений спосіб набути корисних звичок і позбутися звичок шкідливих",
            author = "Джеймс Клир",
            description = R.string.self_care_book_2,
            image = "https://content1.rozetka.com.ua/goods/images/big/186939123.jpg",
            type = BookType.SELF_CARE
        ),
        Book(
            title = "Здоровий кишечник. Контроль ваги, настрою та здоров’я",
            author = "Дж. Зонненбурґ, Е. Зонненбурґ",
            description = R.string.self_care_book_3,
            image = "https://content.rozetka.com.ua/goods/images/big/186993019.jpg",
            type = BookType.SELF_CARE
        ),
        Book(
            title = "Self-care. Забота о себе для современной ведьмы. Магические способы побаловать себя, питающие и укрепляющие тело и дух",
            author = "Ерін Мерфі-Гіскок",
            description = R.string.self_care_book_4,
            image = "https://content2.rozetka.com.ua/goods/images/big/168535061.jpg",
            type = BookType.SELF_CARE
        ),
        Book(
            title = "Жити із \"зеленим серцем\". Подбай про себе і планету",
            author = "Ґей Браун",
            description = R.string.self_care_book_5,
            image = "https://content2.rozetka.com.ua/goods/images/big/32849846.png",
            type = BookType.SELF_CARE
        ),
        Book(
            title = "Принципы. Жизнь и работа",
            author = "Рэй Далио",
            description = R.string.work_life_book_2,
            image = "https://content1.rozetka.com.ua/goods/images/big/197589883.jpg",
            type = BookType.WORK_LIFE_BALANCE
        ),
        Book(
            title = "Думай как Илон Маск. И другие простые стратегии для гигантского скачка в работе и жизни",
            author = "Озан Варол",
            description = R.string.work_life_book_3,
            image = "https://content.rozetka.com.ua/goods/images/big/196334536.jpg",
            type = BookType.WORK_LIFE_BALANCE
        ),
        Book(
            title = "Робота рулить! Уроки Google. Правила гри у команді мрії",
            author = "Бок Ласло",
            description = R.string.work_life_book_4,
            image = "https://content.rozetka.com.ua/goods/images/big/67310013.jpg",
            type = BookType.WORK_LIFE_BALANCE
        ),
        Book(
            title = "Кар’єра без драм і травм",
            author = "Анна Мазур, Настя Пасенко",
            description = R.string.work_life_book_5,
            image = "https://content.rozetka.com.ua/goods/images/big/236056612.jpg",
            type = BookType.WORK_LIFE_BALANCE
        ),
        Book(
            title = "Рік у Провансі",
            author = "Питер Мейл",
            description = R.string.other_book_2,
            image = "https://content1.rozetka.com.ua/goods/images/big/161135658.jpg",
            type = BookType.OTHER
        ),
        Book(
            title = "Кафе на краю світу",
            author = "Джон П. Стрелеки",
            description = R.string.other_book_3,
            image = "https://content1.rozetka.com.ua/goods/images/big/222409612.jpg",
            type = BookType.OTHER
        ),
        Book(
            title = "Дрібних мрій не буває. Про сміливість, уяву та становлення сучасного Ізраїлю",
            author = "Шимон Перес",
            description = R.string.other_book_4,
            image = "https://content.rozetka.com.ua/goods/images/big/135441516.jpg",
            type = BookType.OTHER
        ),
        Book(
            title = "Фактор Черчилля. Як одна людина змінила історію",
            author = "Боріс Джонсон",
            description = R.string.other_book_5,
            image = "https://content2.rozetka.com.ua/goods/images/big/181360688.jpg",
            type = BookType.OTHER
        ),
        Book(
            title = "4D БРЕНДИНГ: зламуючи корпоративний код мережевої економіки",
            author = "Томас Ґед",
            description = R.string.communication_book_2,
            image = "https://s.mind.ua/img/forall/books/1/18.jpg",
            type = BookType.COMMUNICATION
        ),
        Book(
            title = "Великі бренди - великі проблеми: Чим більш відома компанія, тим важчий її шлях.",
            author = "Джек Траут",
            description = R.string.communication_book_3,
            image = "https://s.mind.ua/img/forall/books/1/20.jpg",
            type = BookType.COMMUNICATION
        ),
        Book(
            title = "Мобільності",
            author = "Джон Уррі",
            description = R.string.communication_book_4,
            image = "https://s.mind.ua/img/forall/books/1/22.jpg",
            type = BookType.COMMUNICATION
        ),
        Book(
            title = "Персональний брендинг: технології досягнення особ. популярності",
            author = "Філіп Котлер, Ірвінг Рейн, Майкл Хемлін, Мартін Стіллер",
            description = R.string.communication_book_5,
            image = "https://s.mind.ua/img/forall/books/1/12.jpg",
            type = BookType.COMMUNICATION
        ),
        Book(
            title = "Легкий спосіб перестати відкладати справи на потім.",
            author = "Нейл Фьоре",
            description = R.string.procrastination_book_2,
            image = "https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/3/4/341293_20626740.jpg",
            type = BookType.PROCRASTINATION
        ),
        Book(
            title = "Прокрастинація та самосаботаж.",
            author = "Эйлин Леви",
            description = R.string.procrastination_book_3,
            image = "http://loveread.ec/img/photo_books/59587.jpg",
            type = BookType.PROCRASTINATION
        ),
        Book(
            title = "Мистецтво прокрастинації: як правильно тягнути час, лопотрясувати і відкладати на завтра.",
            author = "Джон Перрі",
            description = R.string.procrastination_book_4,
            image = "https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/i/m/img280_47.jpg",
            type = BookType.PROCRASTINATION
        ),
        Book(
            title = "Прокрастинація. Перша допомога.",
            author = "Хенри Шувенбург, Таня ван Эссен",
            description = R.string.procrastination_book_5,
            image = "https://img.yakaboo.ua/media/catalog/product/cache/1/image/398x565/31b681157c4c1a5551b0db4896e7972f/6/0/6000036964.jpg",
            type = BookType.PROCRASTINATION
        ),
        Book(
            title = "Вдивляючись у сонце. Долаючи страх смерті.",
            author = "Ірвін Ялом",
            description = R.string.anxiety_book_2,
            image = "https://book-ye.com.ua/upload/medialibrary/247/Vdivlyayuchis-u-sontse.-Dolayuchi-strakh-smerti.jpg",
            type = BookType.ANXIETY
        ),
        Book(
            title = "Любити те, що є.",
            author = "Байрен Кейті",
            description = R.string.anxiety_book_3,
            image = "https://book-ye.com.ua/upload/medialibrary/84b/Lyubiti-te_-shcho-_.jpg",
            type = BookType.ANXIETY
        ),
        Book(
            title = "Обирай себе. Як подолати страх та сумніви на шляху до повноцінного життя.",
            author = "Ембер Рей",
            description = R.string.anxiety_book_4,
            image = "https://book-ye.com.ua/upload/medialibrary/df3/Obiray-sebe.jpg",
            type = BookType.ANXIETY
        ),
        Book(
            title = "Перемикайся. Стань тим, ким хочеш бути.",
            author = "Маршалл Ґолдсміт, Марк Рейтер",
            description = R.string.anxiety_book_5,
            image = "https://book-ye.com.ua/upload/medialibrary/f8b/Peremikaysya.-Stan-tim_-kim-khochesh-buti.jpg",
            type = BookType.ANXIETY
        )
    )
}
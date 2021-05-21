package com.anime.dl.sources

import com.anime.dl.App
import com.anime.dl.R
import com.anime.dl.sources.models.AnimeInfo
import com.anime.dl.sources.models.AnimePage
import com.anime.dl.sources.models.EpisodeInfo

class TutorialSource : Source {

    override val id = 0L

    override val name = App.applicationContext().getString(R.string.tutorial_source)

    override val lang = "all"

    override fun getAnimeList(page: Int): AnimePage {
        return AnimePage(getTutorialAnime(page), true)
    }

    override fun getAnimeDetails(anime: AnimeInfo): AnimeInfo { return anime }

    override fun getEpisodeList(anime: AnimeInfo): List<EpisodeInfo> {
        val pattern = "anime/([0-9]+)/".toRegex()
        val match = pattern.find(anime.link)
        var (aniId) = match!!.destructured
        aniId = aniId.toString()
        val default = listOf(
            mapOf(
                "number" to "1",
                "title" to "Episode 1"
            ),
            mapOf(
                "number" to "2",
                "title" to "Episode 1"
            ),
            mapOf(
                "number" to "3",
                "title" to "Episode 1"
            ),
            mapOf(
                "number" to "4",
                "title" to "Episode 1"
            ),
            mapOf(
                "number" to "5",
                "title" to "Episode 1"
            )
        )

        val animes = mapOf(
            "20832" to listOf(
                    mapOf(
                        "number" to "1",
                        "title" to "End and Beginning",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/4kP9LokDvo8houeyTzBBuV8R1zE.jpg"
                    ),
                    mapOf(
                        "number" to "2",
                        "title" to "Floor Guardians",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/5o2lrKAxesQgnxpaJVhwWXUWP7U.jpg"
                    ),
                    mapOf(
                        "number" to "3",
                        "title" to "The Battle of Carne Village",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/xwHETk5U7IQuKvugwH07lqUk1sG.jpg"
                    ),
                    mapOf(
                        "number" to "4",
                        "title" to "Ruler of Death",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/bqo2kd8GL2YzSnh8WOVV6zeJQEi.jpg"
                    ),
                    mapOf(
                        "number" to "5",
                        "title" to "Two Adventurers",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/1vKkF3gYCnQuzDMUX8AdLzArPlI.jpg"
                    ),
                    mapOf(
                        "number" to "6",
                        "title" to "Journey",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/alfzJA56yI1JUr5E6BVeayRuUIa.jpg"
                    ),
                    mapOf(
                        "number" to "7",
                        "title" to "Wise King of Forest",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/9hvjs6o3rml3XweLkTwAtJMCWU9.jpg"
                    ),
                    mapOf(
                        "number" to "8",
                        "title" to "Twin Swords of Slashing Death",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/qIAnSTmlbf11vHbeHPnZ0bIGStN.jpg"
                    ),
                    mapOf(
                        "number" to "9",
                        "title" to "The Dark Warrior",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/AqW3PZN2qOPN8OSqBdbnIuY4AUJ.jpg"
                    ),
                    mapOf(
                        "number" to "10",
                        "title" to "True Vampire",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/yPz3YYvU8efYVyrMcv6Ij2ZoVpE.jpg"
                    ),
                    mapOf(
                        "number" to "11",
                        "title" to "Confusion and Understanding",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/fUtckv6eojYgH9KrRQxztBcnucM.jpg"
                    ),
                    mapOf(
                        "number" to "12",
                        "title" to "The Bloody Valkyrie",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/j6vZwPs3cMfWhTGDDtFH2ckR8I3.jpg"
                    ),
                    mapOf(
                        "number" to "13",
                        "title" to "Player VS Non Player Character",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/hJf6boqyltf1YNJ0KqDIi68MxBO.jpg"
                    )
            ),
            "98437" to listOf(
                    mapOf(
                        "number" to "1",
                        "title" to "The Dawn of Despair",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/VNH6KUwz9s6NUOBq743WUKzCoQ.jpg"
                    ),
                    mapOf(
                        "number" to "2",
                        "title" to "Departure",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/uaRpG02CiAkVkzzJfD7tM6nojSK.jpg"
                    ),
                    mapOf(
                        "number" to "3",
                        "title" to "Lizard Men, Gathering",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/ejax8jW5gp75r362PiymeugWLxt.jpg"
                    ),
                    mapOf(
                        "number" to "4",
                        "title" to "Army of Death",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/jhapIiQz6QWRROxRyo7VYw5uz6A.jpg"
                    ),
                    mapOf(
                        "number" to "5",
                        "title" to "The Freezing God",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/pEnciyobmifN1ApNL3x99Ou9BG8.jpg"
                    ),
                    mapOf(
                        "number" to "6",
                        "title" to "Those Who Pick Up, Those Who Are Picked Up",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/oxpjVxdIhB0ikGDfH821lJnXnKA.jpg"
                    ),
                    mapOf(
                        "number" to "7",
                        "title" to "Blue Roses",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/zjuGVNiSV97XPBzbCQBAYEOK9hI.jpg"
                    ),
                    mapOf(
                        "number" to "8",
                        "title" to "A Boy’s Feeling",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/2aEdzdH0ifBImZL2eVEFKwae0PK.jpg"
                    ),
                    mapOf(
                        "number" to "9",
                        "title" to "Soaring Sparks of Fire",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/diNisbwHkTEM6vkQlJRyEwikykB.jpg"
                    ),
                    mapOf(
                        "number" to "10",
                        "title" to "Disturbance Begins in the Royal Capital",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/ap6Pic1Vm5wWEyG0YuLBtbcyhjJ.jpg"
                    ),
                    mapOf(
                        "number" to "11",
                        "title" to "Jaldabaoth",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/is8hObrVoJhOUi5s5S7eMAEmEOF.jpg"
                    ),
                    mapOf(
                        "number" to "12",
                        "title" to "The Final Battle of the Disturbance",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/qRAnxDWlk2HRHiEBUvMHIPJgFeI.jpg"
                    ),
                    mapOf(
                        "number" to "13",
                        "title" to "The Ultimate Trump Card",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/jLxv81N8jcKlQlYXYA9R2BM39aA.jpg"
                    )
            ),
            "101474" to listOf(
                    mapOf(
                        "number" to "1",
                        "title" to "A Ruler’s Melancholy",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/ndK5SF1xvtSuLlNInWyybGOIo3m.jpg"
                    ),
                    mapOf(
                        "number" to "2",
                        "title" to "Carne Village Once More",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/gqpAmd8lfkaQsYCSZG0F0WObo9y.jpg"
                    ),
                    mapOf(
                        "number" to "3",
                        "title" to "Enri’s Upheaval and Hectic Days",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/nBtno6Z2cNEQlcqfeyZBxzHKkEx.jpg"
                    ),
                    mapOf(
                        "number" to "4",
                        "title" to "Giant of the East, Demon Snake of the West",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/37Jmrqot5NsGiFWTpT4PDJ652UA.jpg"
                    ),
                    mapOf(
                        "number" to "5",
                        "title" to "Two Leaders",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/oMqo6oyjnrwaDXC1M2HGB4FbJeh.jpg"
                    ),
                    mapOf(
                        "number" to "6",
                        "title" to "Invitation to Death",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/hpw9A72rlcnnLY0ooQj3rPY6Axg.jpg"
                    ),
                    mapOf(
                        "number" to "7",
                        "title" to "Butterfly Entangled in a Spider’s Web",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/kYeA85N4nZQy9Qqk0f97EiSKEXK.jpg"
                    ),
                    mapOf(
                        "number" to "8",
                        "title" to "A Handful of Hope",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/oWWXfvqNzRad3ZKyELAL9Mvn5AY.jpg"
                    ),
                    mapOf(
                        "number" to "9",
                        "title" to "War of Words",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/rDzUMWUt4frNEyPRnYJHKdjVdRc.jpg"
                    ),
                    mapOf(
                        "number" to "10",
                        "title" to "Preparation for War",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/kW3ITQTE2vvBaDg7JbRZtbv3uk0.jpg"
                    ),
                    mapOf(
                        "number" to "11",
                        "title" to "Another Battle",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/ceJxUSG6RCwNLjOasRJKQWivoYa.jpg"
                    ),
                    mapOf(
                        "number" to "12",
                        "title" to "Massacre",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/o0kr52klt1Qv3EwQjpCrM7gwd4M.jpg"
                    ),
                    mapOf(
                        "number" to "13",
                        "title" to "Player vs Player",
                        "thumbnail" to "https://www.themoviedb.org/t/p/original/9kpZeXSUhmwqpNlbK1u6m1hc80h.jpg"
                    )
            ),
            "6702" to default,
            "20626" to default,
            "99749" to default,
            "21087" to default,
            "97668" to default,
            "21" to default,
            "11061" to default,
            "20" to default,
            "1735" to default,
            "97938" to default,
            "116589" to default,
            "20997" to default,
            "20954" to default,
            "5682" to default,
            "68" to default,
            "14345" to default,
            "1535" to default
        )

        if (animes.containsKey(aniId)) {
            return animes.get(aniId)!!.map {
                EpisodeInfo(
                        key = it["number"].toString(),
                        title = it["number"].toString() + ". " + it["title"].toString(),
                        dateUpload = System.currentTimeMillis(),
                        ep_number = -1f,
                        thumbnail = if (it.containsKey("thumbnail")) {
                            it["thumbnail"].toString()
                        } else {
                            ""
                        }
                )
            }
        } else {
            return emptyList()
            // this line should never execute, but I just added it in, just in case
        }
    }

    private fun getTutorialAnime(page: Int): List<AnimeInfo> {
        val list = mutableListOf<AnimeInfo>()
        val id = (page - 1) * 20 + 1

        val animes = listOf(
            mapOf(
                "title" to "Overlord",
                "link" to "https://anilist.co/anime/20832/Overlord/",
                "description" to "The story takes place in the year 2138 when virtual reality gaming is booming. Yggdrasil, a popular online game is quietly shut down one day. However, the protagonist Momonga decides to not log out. Momonga is then transformed into the image of a skeleton as \"the most powerful wizard.\" The world continues to change, with non-player characters (NPCs) beginning to feel emotion. Having no parents, friends, or place in society, this ordinary young man Momonga then strives to take over the new world the game has become.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20832-Kz7PMdGT0JI6.jpg",
                "genres" to listOf("Action", "Adventure", "Fantasy")
            ),
            mapOf(
                "title" to "Overlord II",
                "link" to "https://anilist.co/anime/98437/Overlord-II/",
                "description" to "The second season of Overlord.\n\nContinuing his quest to rule this new world, Ains begins to put the pieces in place for his conquest. But between a Lizard Man tribe uprising, and performing missions as adamantite adventurer Lord Momon, Ains has his work cut out for him! Thankfully he has his most loyal and willing subordinates to do his bidding. Nothing and no one can stop the supreme and mighty Overlord in his quest to rule all!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx98437-5q0GWqHhNAgJ.jpg",
                "genres" to listOf("Action", "Adventure", "Fantasy")
            ),
            mapOf(
                "title" to "Overlord III",
                "link" to "https://anilist.co/anime/101474/Overlord-III/",
                "description" to "The third season of Overlord.\n\nWith his foundations now set in this new world, the first steps of Ains Ooal Gown's master plan apparent begin to come to fruition. The value of Carne village and especially the political value of his alter ego Momon are reaffirmed; the “hidden” genius of his actions is continuously met with shock and awe by Demiurge, Albedo, the rest of his guardians, and even Ains himself at his “great wisdom.” His attempts to act in a way befitting the ruler of Nazarick, continuing to further cement his guardian’s loyalty, push him ever further down the path of not-so-intentional world domination. Without human conscience due to being undead, he is motivated only to continue searching for other players from Yggdrasil and to gain power to better protect the children and home of him and his forty former companions. Lord Ains Ooal Gown maintains his mantle of Overlord and leads the Great Tomb of Nazarick unto the world stage, directly into a vicious power struggle between two great empires.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx101474-XxEnwSLw29Q9.jpg",
                "genres" to listOf("Action", "Adventure", "Fantasy")
            ),
            mapOf(
                "title" to "Fairy Tail (2009)",
                "link" to "https://anilist.co/anime/6702/Fairy-Tail/",
                "description" to "Across the Fiore kingdom, wizards join guilds and make their pay by filling magical needs—but one guild has a reputation as the roughest, rowdiest, most dangerous of all: Fairy Tail!\nWhen four young Fairy Tail members unite, their bond is forged by a power found in neither muscle nor magic and grows stronger with every mission. Whatever you do, don't mess with these friends or you'll get a taste of Natsu's flaming fist or Gray's ice hammer, suffer a painful blow from one of Lucy's celestial spirits or catch the edge of Erza's mighty blade! Whether they're stopping demons from devastating the world or wrestling in the mess hall, this mystical team manages to inflict as much damage to their rivals as they do to the surrounding area!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx6702-4cW6E5AqQqqB.png",
                "genres" to listOf("Action", "Adventure", "Comedy", "Fantasy")
            ),
            mapOf(
                "title" to "Fairy Tail (2014)",
                "link" to "https://anilist.co/anime/20626/Fairy-Tail-2014/",
                "description" to "The Grand Magic Games reaches its climax following Natsu Dragneel and Gajeel Redfox's stunning victory over Sting Eucliffe and Rogue Cheney of the Sabertooth guild. This success pushes the Fairy Tail guild closer to being crowned the overall champions, but obtaining victory isn't the only challenge they face. A mystery still surrounds a hooded stranger and the ominous Eclipse Gate, leaving more questions than answers.\n\nMore crazy adventures are on the horizon for Fairy Tail as their destructive antics and joyful rowdiness continue unabated. Their greatest trial is quickly approaching, but united as a family, the guild will always be ready to face any threat that comes their way.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20626-9LTreIofBgnu.jpg",
                "genres" to listOf("Action", "Adventure", "Comedy", "Fantasy")
            ),
            mapOf(
                "title" to "Fairy Tail (2018)",
                "link" to "https://anilist.co/anime/99749/Fairy-Tail-2018/",
                "description" to "The final season of Fairy Tail.\n\nFairy Tail has been disbanded. A year later, Lucy comes into contact with Natsu and Happy. The three of them try to find the other former members' whereabouts to reconstruct the guild as they seek the real reason behind the guild's disbandment.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx99749-tvz2LCPdMyrp.jpg",
                "genres" to listOf("Action", "Adventure", "Comedy", "Fantasy")
            ),
            mapOf(
                "title" to "One-Punch Man",
                "link" to "https://anilist.co/anime/21087/One-Punch-Man/",
                "description" to "Saitama has a rather peculiar hobby, being a superhero, but despite his heroic deeds and superhuman abilities, a shadow looms over his life. He's become much too powerful, to the point that every opponent ends up defeated with a single punch.\n\nThe lack of challenge has driven him into a state of apathy, as he watches his life pass by having lost all enthusiasm, at least until he's unwillingly thrust in the role of being a mentor to the young and revenge-driven Genos.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21087-8WpMBeqsSVx8.png",
                "genres" to listOf("Action", "Comedy", "Sci-Fi", "Supernatural")
            ),
            mapOf(
                "title" to "One-Punch Man 2",
                "link" to "https://anilist.co/anime/97668/One-Punch-Man-2/",
                "description" to "Saitama is a hero who only became a hero for fun. After three years of “special training,” he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch. Now, the great seer Madame Shibabawa’s prediction about the Earth being doomed seems to be coming true as the frequency of monster incidents escalates. Alongside Genos, his faithful disciple, Saitama begins his official hero duties as a member of the Hero Association, while Garou, a man utterly fascinated by monsters, makes his appearance.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx97668-TAGeFfzxXKnB.jpg",
                "genres" to listOf("Action", "Comedy", "Sci-Fi", "Supernatural")
            ),
            mapOf(
                "title" to "One Piece",
                "link" to "https://anilist.co/anime/21/One-Piece/",
                "description" to "Gold Roger was known as the Pirate King, the strongest and most infamous being to have sailed the Grand Line. The capture and death of Roger by the World Government brought a change throughout the world. His last words before his death revealed the location of the greatest treasure in the world, One Piece. It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece (which promises an unlimited amount of riches and fame), and quite possibly the most coveted of titles for the person who found it, the title of the Pirate King.\n\nEnter Monkey D. Luffy, a 17-year-old boy that defies your standard definition of a pirate. Rather than the popular persona of a wicked, hardened, toothless pirate who ransacks villages for fun, Luffy’s reason for being a pirate is one of pure wonder; the thought of an exciting adventure and meeting new and intriguing people, along with finding One Piece, are his reasons of becoming a pirate. Following in the footsteps of his childhood hero, Luffy and his crew travel across the Grand Line, experiencing crazy adventures, unveiling dark mysteries and battling strong enemies, all in order to reach One Piece.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21-tXMN3Y20PIL9.jpg",
                "genres" to listOf("Action", "Adventure", "Comedy", "Drama", "Fantasy")
            ),
            mapOf(
                "title" to "Hunter x Hunter (2011)",
                "link" to "https://anilist.co/anime/11061/Hunter-x-Hunter-2011/",
                "description" to "A Hunter is one who travels the world doing all sorts of dangerous tasks. From capturing criminals to searching deep within uncharted lands for any lost treasures. Gon is a young boy whose father disappeared long ago, being a Hunter. He believes if he could also follow his father's path, he could one day reunite with him.\n\n After becoming 12, Gon leaves his home and takes on the task of entering the Hunter exam, notorious for its low success rate and high probability of death to become an official Hunter. He befriends the revenge-driven Kurapika, the doctor-to-be Leorio and the rebellious ex-assassin Killua in the exam, with their friendship prevailing throughout the many trials and threats they come upon taking on the dangerous career of a Hunter.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx11061-sIpBprNRfzCe.png",
                "genres" to listOf("Action", "Adventure", "Fantasy")
            ),
            mapOf(
                "title" to "Naruto",
                "link" to "https://anilist.co/anime/20/Naruto/",
                "description" to "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto.\n\nShunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20-E3YH5W6sz6H7.jpg",
                "genres" to listOf("Action", "Comedy")
            ),
            mapOf(
                "title" to "Naruto: Shippuuden",
                "link" to "https://anilist.co/anime/1735/Naruto-Shippuuden/",
                "description" to "Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1735-80JNLAlnxrKj.png",
                "genres" to listOf("Action", "Comedy")
            ),
            mapOf(
                "title" to "Boruto: Naruto Next Generations",
                "link" to "https://anilist.co/anime/97938/Boruto-Naruto-Next-Generations/",
                "description" to "Naruto was a young shinobi with an incorrigible knack for mischief. He achieved his dream to become the greatest ninja in the village and his face sits atop the Hokage monument. But this is not his story... A new generation of ninja are ready to take the stage, led by Naruto's own son, Boruto!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx97938-BnF6M5yTaNB1.jpg",
                "genres" to listOf("Action", "Adventure")
            ),
            mapOf(
                "title" to "86: Eighty Six",
                "link" to "https://anilist.co/anime/116589/86-Eighty-Six/",
                "description" to "Called “Juggernaut,” these are the unmanned combat drones developed by the Republic of San Magnolia in answer to the attacks by the autonomous unmanned drones of the neighboring Empire of Giad, the “Legion”. But they’re only unmanned in name. In reality, they are piloted by the Eighty-sixers—those considered to be less than human and treated as mere tools.\n\nDetermined to achieve his own mysterious ends, Shin, the captain of Spearhead Squadron, which is comprised of Eighty-sixers, continues to fight a hopeless war on a battlefield where only death awaits him.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx116589-WSpNedJdAH3L.jpg",
                "genres" to listOf("Drama", "Mecha", "Sci-Fi")
            ),
            mapOf(
                "title" to "Charlotte",
                "link" to "https://anilist.co/anime/20997/Charlotte/",
                "description" to "The story centers around the special abilities that occur among a small percentage of boys and girls in puberty. Yuu Otosaka uses his power without others knowing, and lives a fairly normal, average school life. Before him suddenly appears a girl, Nao Tomori. Due to his meeting with her, the fate of special power-users will be exposed.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20997-FGhaAtfnXCsH.jpg",
                "genres" to listOf("Comedy", "Drama", "Romance", "Sci-Fi", "Supernatural")
            ),
            mapOf(
                "title" to "Koe no Katachi",
                "link" to "https://anilist.co/anime/20954/Koe-no-Katachi/",
                "description" to "After transferring into a new school, a deaf girl, Shouko Nishimiya, is bullied by the popular Shouya Ishida. As Shouya continues to bully Shouko, the class turns its back on him. Shouko transfers and Shouya grows up as an outcast. Alone and depressed, the regretful Shouya finds Shouko to make amends.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20954-RYEF5mWglzV8.png",
                "genres" to listOf("Drama", "Romance", "Slice of Life")
            ),
            mapOf(
                "title" to "Phantom: Requiem for the Phantom",
                "link" to "https://anilist.co/anime/5682/Phantom-Requiem-for-the-Phantom/",
                "description" to "Mafia is rife in America where assassinations are a regular occurrence on the streets. Inferno, a mysterious company, is behind most of these dealings through the use of their near-invincible human weapon, \"Phantom.\"\n\nOne day, a Japanese tourist accidentally witnesses Phantom’s latest murder. Desperate to escape, the tourist hides in a secluded building. However, Phantom, revealed to be a young woman named Ein, and the leader of Inferno ‘Scythe Master’ captures the tourist and brainwashes him.\n\nGiven the name ’Zwei’, this once peaceful tourist is now a puppet of Inferno with no memories. Drawn into a world of lies, deceit, and violence, Zwei must fight to survive, hopefully to one day regain his memories and escape from this world where he is constantly on the brink of death.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx5682-13S0UNmJInAE.png",
                "genres" to listOf("Action", "Drama", "Thriller")
            ),
            mapOf(
                "title" to "Black Cat",
                "link" to "https://anilist.co/anime/68/Black-Cat/",
                "description" to "Train Heartnet is the infamous gunman 'Black Cat', a cold-blooded assassin who works for the organization known as 'Chronos', bearing the mark XIII on his body. Most of his life was surrounded by violence, so Train is quiet, asocial and uncaring. However his life changes as he encounters the mysterious Sweeper (bounty hunter), Saya. Her unique way of living interested Train but tragedy occurs, resulting in Train changing the way he has lived towards the life of a Sweeper with newfound friends Sven and Eve.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx68-DKUkbrlNTPSy.png",
                "genres" to listOf("Drama", "Comedy", "Sci-Fi")
            ),
            mapOf(
                "title" to "BTOOOM!",
                "link" to "https://anilist.co/anime/14345/BTOOOM/",
                "description" to "In the blink of the mind's eye, Ryouta Sakamoto suddenly finds himself transported from playing the hit Btoom! video game to being stranded on a mysterious island, equipped with a day's worth of provisions, a bag of bombs, a strange crystal embedded in his left hand and a huge gaping hole in his memory. But it doesn't take long to figure out what's going on, especially after the first person Ryouta meets tries to kill him. Someone is attempting to recreate the ultra-violent Btoom! game in real life, and the island has been filled with an army of other unwilling players, each armed with one of the multiple variants of explosive weapons called BIM. Fortunately, Ryouta's an ace Btoom! player, but this insane version of the game has no reset switch or second lives, and there's only one way off the island: kill seven other people before they can kill you! Can Ryouta repurpose his game based skills fast enough to survive?",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx14345-JGV1lFS9XW9w.jpg",
                "genres" to listOf("Drama", "Psychological", "Sci-Fi")
            ),
            mapOf(
                "title" to "Death Note",
                "link" to "https://anilist.co/anime/1535/Death-Note/",
                "description" to "Light Yagami is a genius high school student who is about to learn about life through a book of death. When a bored shinigami, a God of Death, named Ryuk drops a black notepad called a Death Note, Light receives power over life and death with the stroke of a pen. Determined to use this dark gift for the best, Light sets out to rid the world of evil… namely, the people he believes to be evil. Should anyone hold such power?\n\nThe consequences of Light’s actions will set the world ablaze.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1535-lawCwhzhi96X.jpg",
                "genres" to listOf("Mystery", "Psychological", "Supernatural", "Thriller")
            ),
        )
        for (anime in animes) {
            list += AnimeInfo(
                key="$id",
                title=anime["title"].toString(),
                link=anime["link"].toString(),
                description=anime["description"].toString(),
                cover=anime["img"].toString(),
                genres=if (anime.containsKey("genres")) {
                    anime["genres"] as List<String>
                } else {
                    emptyList()
                }
            )
        }
        return list
    }
}

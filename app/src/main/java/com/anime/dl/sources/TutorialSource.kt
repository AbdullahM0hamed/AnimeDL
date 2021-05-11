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
        val episode1 = EpisodeInfo(
            "1",
            "Episode 1",
            System.currentTimeMillis()
        )

        val episode2 = episode1.copy("2", "Episode 2")
        val episode3 = episode1.copy("3", "Episode 3")

        return listOf(episode1, episode2, episode3)
    }

    private fun getTutorialAnime(page: Int): List<AnimeInfo> {
        val list = mutableListOf<AnimeInfo>()
        val id = (page - 1) * 20 + 1

        val animes = listOf(
            mapOf(
                "title" to "Overlord",
                "description" to "The story takes place in the year 2138 when virtual reality gaming is booming. Yggdrasil, a popular online game is quietly shut down one day. However, the protagonist Momonga decides to not log out. Momonga is then transformed into the image of a skeleton as \"the most powerful wizard.\" The world continues to change, with non-player characters (NPCs) beginning to feel emotion. Having no parents, friends, or place in society, this ordinary young man Momonga then strives to take over the new world the game has become.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20832-Kz7PMdGT0JI6.jpg"
            ),
            mapOf(
                "title" to "Overlord II",
                "description" to "The second season of Overlord.\n\nContinuing his quest to rule this new world, Ains begins to put the pieces in place for his conquest. But between a Lizard Man tribe uprising, and performing missions as adamantite adventurer Lord Momon, Ains has his work cut out for him! Thankfully he has his most loyal and willing subordinates to do his bidding. Nothing and no one can stop the supreme and mighty Overlord in his quest to rule all!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx98437-5q0GWqHhNAgJ.jpg"
            ),
            mapOf(
                "title" to "Overlord III",
                "description" to "The third season of Overlord.\n\nWith his foundations now set in this new world, the first steps of Ains Ooal Gown's master plan apparent begin to come to fruition. The value of Carne village and especially the political value of his alter ego Momon are reaffirmed; the “hidden” genius of his actions is continuously met with shock and awe by Demiurge, Albedo, the rest of his guardians, and even Ains himself at his “great wisdom.” His attempts to act in a way befitting the ruler of Nazarick, continuing to further cement his guardian’s loyalty, push him ever further down the path of not-so-intentional world domination. Without human conscience due to being undead, he is motivated only to continue searching for other players from Yggdrasil and to gain power to better protect the children and home of him and his forty former companions. Lord Ains Ooal Gown maintains his mantle of Overlord and leads the Great Tomb of Nazarick unto the world stage, directly into a vicious power struggle between two great empires.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx101474-XxEnwSLw29Q9.jpg"
            ),
            mapOf(
                "title" to "Fairy Tail (2009)",
                "description" to "Across the Fiore kingdom, wizards join guilds and make their pay by filling magical needs—but one guild has a reputation as the roughest, rowdiest, most dangerous of all: Fairy Tail!\nWhen four young Fairy Tail members unite, their bond is forged by a power found in neither muscle nor magic and grows stronger with every mission. Whatever you do, don't mess with these friends or you'll get a taste of Natsu's flaming fist or Gray's ice hammer, suffer a painful blow from one of Lucy's celestial spirits or catch the edge of Erza's mighty blade! Whether they're stopping demons from devastating the world or wrestling in the mess hall, this mystical team manages to inflict as much damage to their rivals as they do to the surrounding area!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx6702-4cW6E5AqQqqB.png"
            ),
            mapOf(
                "title" to "Fairy Tail (2014)",
                "description" to "The Grand Magic Games reaches its climax following Natsu Dragneel and Gajeel Redfox's stunning victory over Sting Eucliffe and Rogue Cheney of the Sabertooth guild. This success pushes the Fairy Tail guild closer to being crowned the overall champions, but obtaining victory isn't the only challenge they face. A mystery still surrounds a hooded stranger and the ominous Eclipse Gate, leaving more questions than answers.\n\nMore crazy adventures are on the horizon for Fairy Tail as their destructive antics and joyful rowdiness continue unabated. Their greatest trial is quickly approaching, but united as a family, the guild will always be ready to face any threat that comes their way.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20626-9LTreIofBgnu.jpg"
            ),
            mapOf(
                "title" to "Fairy Tail (2018)",
                "description" to "The final season of Fairy Tail.\n\nFairy Tail has been disbanded. A year later, Lucy comes into contact with Natsu and Happy. The three of them try to find the other former members' whereabouts to reconstruct the guild as they seek the real reason behind the guild's disbandment.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx99749-tvz2LCPdMyrp.jpg"
            ),
            mapOf(
                "title" to "One-Punch Man",
                "description" to "Saitama has a rather peculiar hobby, being a superhero, but despite his heroic deeds and superhuman abilities, a shadow looms over his life. He's become much too powerful, to the point that every opponent ends up defeated with a single punch.\n\nThe lack of challenge has driven him into a state of apathy, as he watches his life pass by having lost all enthusiasm, at least until he's unwillingly thrust in the role of being a mentor to the young and revenge-driven Genos.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21087-8WpMBeqsSVx8.png"
            ),
            mapOf(
                "title" to "One-Punch Man 2",
                "description" to "Saitama is a hero who only became a hero for fun. After three years of “special training,” he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch. Now, the great seer Madame Shibabawa’s prediction about the Earth being doomed seems to be coming true as the frequency of monster incidents escalates. Alongside Genos, his faithful disciple, Saitama begins his official hero duties as a member of the Hero Association, while Garou, a man utterly fascinated by monsters, makes his appearance.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx97668-TAGeFfzxXKnB.jpg"
            ),
            mapOf(
                "title" to "One Piece",
                "description" to "Gold Roger was known as the Pirate King, the strongest and most infamous being to have sailed the Grand Line. The capture and death of Roger by the World Government brought a change throughout the world. His last words before his death revealed the location of the greatest treasure in the world, One Piece. It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece (which promises an unlimited amount of riches and fame), and quite possibly the most coveted of titles for the person who found it, the title of the Pirate King.\n\nEnter Monkey D. Luffy, a 17-year-old boy that defies your standard definition of a pirate. Rather than the popular persona of a wicked, hardened, toothless pirate who ransacks villages for fun, Luffy’s reason for being a pirate is one of pure wonder; the thought of an exciting adventure and meeting new and intriguing people, along with finding One Piece, are his reasons of becoming a pirate. Following in the footsteps of his childhood hero, Luffy and his crew travel across the Grand Line, experiencing crazy adventures, unveiling dark mysteries and battling strong enemies, all in order to reach One Piece.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21-tXMN3Y20PIL9.jpg"
            ),
            mapOf(
                "title" to "Hunter x Hunter (2011)",
                "description" to "A Hunter is one who travels the world doing all sorts of dangerous tasks. From capturing criminals to searching deep within uncharted lands for any lost treasures. Gon is a young boy whose father disappeared long ago, being a Hunter. He believes if he could also follow his father's path, he could one day reunite with him.\n\n After becoming 12, Gon leaves his home and takes on the task of entering the Hunter exam, notorious for its low success rate and high probability of death to become an official Hunter. He befriends the revenge-driven Kurapika, the doctor-to-be Leorio and the rebellious ex-assassin Killua in the exam, with their friendship prevailing throughout the many trials and threats they come upon taking on the dangerous career of a Hunter.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx11061-sIpBprNRfzCe.png"
            ),
            mapOf(
                "title" to "Naruto",
                "description" to "Naruto Uzumaki, a hyperactive and knuckle-headed ninja, lives in Konohagakure, the Hidden Leaf village. Moments prior to his birth, a huge demon known as the Kyuubi, the Nine-tailed Fox, attacked Konohagakure and wreaked havoc. In order to put an end to the Kyuubi's rampage, the leader of the village, the 4th Hokage, sacrificed his life and sealed the monstrous beast inside the newborn Naruto.\n\nShunned because of the presence of the Kyuubi inside him, Naruto struggles to find his place in the village. He strives to become the Hokage of Konohagakure, and he meets many friends and foes along the way.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20-E3YH5W6sz6H7.jpg"
            ),
            mapOf(
                "title" to "Naruto: Shippuuden",
                "description" to "Naruto: Shippuuden is the continuation of the original animated TV series Naruto. The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as he has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1735-80JNLAlnxrKj.png"
            ),
            mapOf(
                "title" to "Boruto: Naruto Next Generations",
                "description" to "Naruto was a young shinobi with an incorrigible knack for mischief. He achieved his dream to become the greatest ninja in the village and his face sits atop the Hokage monument. But this is not his story... A new generation of ninja are ready to take the stage, led by Naruto's own son, Boruto!",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx97938-BnF6M5yTaNB1.jpg"
            ),
            mapOf(
                "title" to "86: Eighty Six",
                "description" to "Called “Juggernaut,” these are the unmanned combat drones developed by the Republic of San Magnolia in answer to the attacks by the autonomous unmanned drones of the neighboring Empire of Giad, the “Legion”. But they’re only unmanned in name. In reality, they are piloted by the Eighty-sixers—those considered to be less than human and treated as mere tools.\n\nDetermined to achieve his own mysterious ends, Shin, the captain of Spearhead Squadron, which is comprised of Eighty-sixers, continues to fight a hopeless war on a battlefield where only death awaits him.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx116589-WSpNedJdAH3L.jpg"
            ),
            mapOf(
                "title" to "Charlotte",
                "description" to "The story centers around the special abilities that occur among a small percentage of boys and girls in puberty. Yuu Otosaka uses his power without others knowing, and lives a fairly normal, average school life. Before him suddenly appears a girl, Nao Tomori. Due to his meeting with her, the fate of special power-users will be exposed.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20997-FGhaAtfnXCsH.jpg"
            ),
            mapOf(
                "title" to "Koe no Katachi",
                "description" to "After transferring into a new school, a deaf girl, Shouko Nishimiya, is bullied by the popular Shouya Ishida. As Shouya continues to bully Shouko, the class turns its back on him. Shouko transfers and Shouya grows up as an outcast. Alone and depressed, the regretful Shouya finds Shouko to make amends.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx20954-RYEF5mWglzV8.png"
            ),
            mapOf(
                "title" to "Phantom: Requiem for the Phantom",
                "description" to "Mafia is rife in America where assassinations are a regular occurrence on the streets. Inferno, a mysterious company, is behind most of these dealings through the use of their near-invincible human weapon, \"Phantom.\"\n\nOne day, a Japanese tourist accidentally witnesses Phantom’s latest murder. Desperate to escape, the tourist hides in a secluded building. However, Phantom, revealed to be a young woman named Ein, and the leader of Inferno ‘Scythe Master’ captures the tourist and brainwashes him.\n\nGiven the name ’Zwei’, this once peaceful tourist is now a puppet of Inferno with no memories. Drawn into a world of lies, deceit, and violence, Zwei must fight to survive, hopefully to one day regain his memories and escape from this world where he is constantly on the brink of death.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx5682-13S0UNmJInAE.png"
            ),
            mapOf(
                "title" to "Black Cat",
                "description" to "Train Heartnet is the infamous gunman 'Black Cat', a cold-blooded assassin who works for the organization known as 'Chronos', bearing the mark XIII on his body. Most of his life was surrounded by violence, so Train is quiet, asocial and uncaring. However his life changes as he encounters the mysterious Sweeper (bounty hunter), Saya. Her unique way of living interested Train but tragedy occurs, resulting in Train changing the way he has lived towards the life of a Sweeper with newfound friends Sven and Eve.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx68-DKUkbrlNTPSy.png"
            ),
            mapOf(
                "title" to "BTOOOM!",
                "description" to "In the blink of the mind's eye, Ryouta Sakamoto suddenly finds himself transported from playing the hit Btoom! video game to being stranded on a mysterious island, equipped with a day's worth of provisions, a bag of bombs, a strange crystal embedded in his left hand and a huge gaping hole in his memory. But it doesn't take long to figure out what's going on, especially after the first person Ryouta meets tries to kill him. Someone is attempting to recreate the ultra-violent Btoom! game in real life, and the island has been filled with an army of other unwilling players, each armed with one of the multiple variants of explosive weapons called BIM. Fortunately, Ryouta's an ace Btoom! player, but this insane version of the game has no reset switch or second lives, and there's only one way off the island: kill seven other people before they can kill you! Can Ryouta repurpose his game based skills fast enough to survive?",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx14345-JGV1lFS9XW9w.jpg"
            ),
            mapOf(
                "title" to "Death Note",
                "description" to "Light Yagami is a genius high school student who is about to learn about life through a book of death. When a bored shinigami, a God of Death, named Ryuk drops a black notepad called a Death Note, Light receives power over life and death with the stroke of a pen. Determined to use this dark gift for the best, Light sets out to rid the world of evil… namely, the people he believes to be evil. Should anyone hold such power?\n\nThe consequences of Light’s actions will set the world ablaze.",
                "img" to "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1535-lawCwhzhi96X.jpg"
            ),
        )
        for (anime in animes) {
            list += AnimeInfo(
                key="$id",
                title=anime["title"].toString(),
                description=anime["description"].toString(),
                cover=anime["img"].toString()
            )
        }
        return list
    }
}

package net.dv8tion.discord.commands;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.discord.Downloader;
import net.dv8tion.discord.GoogleSearch;

import org.apache.commons.lang3.StringUtils;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class MyAnimeListCommand extends Command
{
    public static final String ANIME_URL = "http://myanimelist.net/anime/";
    public static final String MANGA_URL = "http://myanimelist.net/manga/";
    public static final String CHARACTER_URL = "http://myanimelist.net/character/";

    @Override
    public void onChat(UserChatEvent e)
    {
        if (!containsCommand(e.getMsg()))
            return;

        String[] args = commandArgs(e.getMsg());
        GoogleSearch search = new GoogleSearch(
                String.format("%s+%s",
                        StringUtils.join(args, "+", 1, args.length),
                        "site:myanimelist.net"));

        e.getGroup().sendMessage(new MessageBuilder()
            .addUserTag(e.getUser(), e.getGroup())
            .addString(": " + search.getSuggestedReturn())
            .build());
    }

    @Override
    public List<String> aliases()
    {
        return Arrays.asList(".mal");
    }

    @Override
    public String commandDescription()
    {
        return "Searches the http://myanimelist.net anime/manga database for anime and manga.";
    }

    @Override
    public String helpMessage()
    {
        return null;
    }

    private String handleSearch(GoogleSearch search)
    {
        String url = search.getUrl(0);
        if (url.contains(ANIME_URL))
        {
            System.out.println("this is anime");
            String webpage = Downloader.webpage("http://myanimelist.net/manga/75989/");
            System.out.println(webpage);
        }
        else if (url.contains(MANGA_URL))
        {
            System.out.println("this is manga");
            String webpage = Downloader.webpage(url);
            System.out.println(webpage);
        }
        else if (url.contains(CHARACTER_URL))
        {
            System.out.println("this is character");
        }
        else
        {
        }
        return null;
    }
}

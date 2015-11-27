package net.dv8tion.discord.commands;

import java.util.Arrays;
import java.util.List;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;
import net.dv8tion.discord.GoogleSearch;

import org.apache.commons.lang3.StringUtils;

public class SearchCommand extends Command
{
	@Override
	public void onChat(UserChatEvent e) {
		String filter = null;
		String[] splitMessage = commandArgs(e.getMsg());
		switch (splitMessage[0]) {
			case ".google":
			case ".g":
				break;
			case ".wiki":
				filter = "wiki";
				break;
			case ".urban":
				filter = "site:urbandictionary.com";
				break;
			default:
				return;
		}

		GoogleSearch search = new GoogleSearch(
		        String.format("%s+%s",
		                StringUtils.join(splitMessage, "+", 1, splitMessage.length),
		                filter));

		e.getGroup().sendMessage(new MessageBuilder()
            .addUserTag(e.getUser(), e.getGroup())
            .addString(": " + search.getSuggestedReturn())
            .build());
	}

    @Override
    public List<String> aliases()
    {
        return Arrays.asList(".g", ".google", ".wiki", ".urban");
    }

    @Override
    public String commandDescription()
    {
        return null;
    }

    @Override
    public String helpMessage()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
# Custom Reactions Plugin

**Custom Reactions** is a lightweight and versatile plugin that allows server owners to create and manage unique chat-based reactions for players. Reactions can be customized to suit your server's theme and style, offering an interactive and engaging player experience.

---

## Features

- **Customizable Reactions:** Create and modify reactions easily.
- **Targeted Actions:** Players can use reactions to interact with others (e.g., `/slap <player>`).
- **Color Support:** Messages support Minecraft color codes using the `&` symbol.
- **Permission-Based System:** Control access to reactions with fine-grained permissions.

---

## Commands

### `/reactions`
- **Description:** Displays a list of all available reactions.
- **Permission:** `customreactions.command.reactions`

### `/<reaction> <target player>`
- **Description:** Executes a specific reaction towards the target player.
- **Permission:**
  - `customreactions.reaction.<reaction>` - Grants access to a specific reaction.
  - `customreactions.reaction.*` - Grants access to all reactions.

**Example:**
```bash
/slap Notch
```
This command triggers the "slap" reaction and sends the configured message to the target player.

---

## Permissions

- `customreactions.command.reactions` - Access to the `/reactions` command.
- `customreactions.reaction.<reaction>` - Permission to use a specific reaction.
- `customreactions.reaction.*` - Permission to use all reactions.

---

## Customization

All reactions can be fully customized:
- **Add New Reactions:** Define your own reaction commands.
- **Edit Messages:** Tailor reaction messages using Minecraft color codes (`&`).
- **Flexible Configuration:** Adjust reactions to fit your server's needs.

---

## Installation

1. Download the plugin `.jar` file.
2. Place it in your server's `plugins` folder.
3. Restart or reload the server.
4. Edit the configuration file to add or customize reactions.

---

## Configuration Example
```yaml
reactions:
  slap:
    messageToTarget: "&c%SENDER% slapped you!!!"
    messageToSender: "&cYou just slapped %TARGET%!"
    particle: "CRIT"
    sound: "ENTITY_PLAYER_ATTACK_STRONG"
    damageNum: 0.1
  hug:
    messageToTarget:  "%SENDER% just sent you a warm hug."
    messageToSender: "You gave %TARGET% a warm hug."
    particle: "VILLAGER_HAPPY"
    sound: "ENTITY_VILLAGER_TRADE"
```
- Use `%SENDER%` to represent the player executing the command.
- Use `%TARGET%` to represent the target player.
- Color codes can be applied with `&` (e.g., `&c` for red).

---

## Support
For any issues, questions, or feature requests, please contact the plugin developer or open a ticket in the support section.

---

## License
This plugin is licensed under Creative Commons 4.0 International (CC BY 4.0). For more information, please refer to the `LICENSE` file included with the plugin.

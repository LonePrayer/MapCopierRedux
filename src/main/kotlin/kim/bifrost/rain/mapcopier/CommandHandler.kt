package kim.bifrost.rain.mapcopier

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.util.NumberConversions
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandContext
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand
import taboolib.module.chat.colored


/**
 * kim.bifrost.rain.mapcopier.CommandHandler
 * MapCopierX
 *
 * @author 寒雨
 * @since 2022/3/14 0:32
 **/
@CommandHeader(name = "mapcopier", aliases = ["mc"], description = "MapCopierX")
object CommandHandler {

    @CommandBody
    val copy = subCommand {
        dynamic("from") {
            dynamic("to") {
                dynamic("x") {
                    dynamic("y") {
                        dynamic("z") {
                            execute<CommandSender> { sender, context, argument: String ->
                                MapCopier.copy(
                                    toBukkitLocation(context.argument(-4)),
                                    toBukkitLocation(context.argument(-3)),
                                    NumberConversions.toInt(context.argument(-2)),
                                    NumberConversions.toInt(context.argument(-1)),
                                    NumberConversions.toInt(argument),
                                )
                                sender.sendMessage("&c[MapCopierRedux] &7请求已发起...".colored())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun toBukkitLocation(`in`: Any): Location {
        val v = `in`.toString().split(",".toRegex()).toTypedArray()
        return Location(
            if (v.isNotEmpty()) Bukkit.getWorld(v[0]) else Bukkit.getWorlds().iterator().next(),
            if (v.size > 1) NumberConversions.toDouble(v[1]) else 0.0,
            if (v.size > 2) NumberConversions.toDouble(v[2]) else 0.0,
            if (v.size > 3) NumberConversions.toDouble(v[3]) else 0.0,
            if (v.size > 4) NumberConversions.toFloat(v[4]) else 0.0f,
            if (v.size > 5) NumberConversions.toFloat(v[5]) else 0.0f
        )
    }
}
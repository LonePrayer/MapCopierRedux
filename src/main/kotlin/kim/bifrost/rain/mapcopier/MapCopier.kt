package kim.bifrost.rain.mapcopier

import com.fastasyncworldedit.core.FaweAPI
import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.math.BlockVector3
import org.bukkit.Location
import taboolib.common.platform.Plugin

object MapCopier : Plugin() {

    fun copy(from: Location, to: Location, x:Int, y:Int, z:Int) {
        val session = WorldEdit.getInstance().newEditSession(FaweAPI.getWorld(to.world!!.name))
        for (_x in 0..x){
            for (_y in 0..y){
                for(_z in 0..z){
                    val locF = from.clone().add(_x.toDouble(),_y.toDouble(),_z.toDouble())
                    val loc = to.clone().add(_x.toDouble(),_y.toDouble(),_z.toDouble())
                    val block = BlockVector3.at(loc.x,loc.y,loc.z)
                    val getBlock = session.getFullBlock(BlockVector3.at(locF.x,locF.y,locF.z))
                    session.setBlock(block, getBlock)
                }
            }
        }
        session.flushQueue()
    }
}

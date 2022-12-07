package solute.framework

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
@file:Suppress("MayBeConstant")

object Versions {
    val kotlin =            "1.3.11"
    val coroutines =        "1.1.0"
    val serialization =     "0.9.1"
    val ktor =              "1.1.1"
}

object Libs {

    val kotlin_common =             "org.jetbrains.kotlin:kotlin-stdlib-common"
    val kotlin_android =            "org.jetbrains.kotlin:kotlin-stdlib"
    val kotlin_jdk8 =               "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    val kotlin_js =                 "org.jetbrains.kotlin:kotlin-stdlib-js"

    val test_common =               "org.jetbrains.kotlin:kotlin-test-common"
    val test_common_annotations =   "org.jetbrains.kotlin:kotlin-test-annotations-common"
    val test_jdk =                  "org.jetbrains.kotlin:kotlin-test"
    val test_jdk_junit =            "org.jetbrains.kotlin:kotlin-test-junit"
    val test_js =                   "org.jetbrains.kotlin:kotlin-test-js"

    val coroutines_common =         "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
    val coroutines_android =        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutines_jdk =            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutines_jdk8 =           "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
    val coroutines_js =             "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}"
    val coroutines_native =         "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"

    val serialization_common =      "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.serialization}"
    val serialization_jdk =         "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
    val serialization_js =          "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.serialization}"
    val serialization_native =      "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.serialization}"

    val ktor_common =               "io.ktor:ktor-client-core:${Versions.ktor}"
    val ktor_jdk =                  "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
    val ktor_jdk_apache =           "io.ktor:ktor-client-apache:${Versions.ktor}"
    val ktor_js =                   "io.ktor:ktor-client-core-js:${Versions.ktor}"
    val ktor_native =               "io.ktor:ktor-client-core-native:${Versions.ktor}"
}
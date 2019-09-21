cmake_minimum_required(VERSION 2.8.9)
project(hello2)

include_directories(/usr/local/include)
add_executable(mygamestatic mygame.cpp)
target_link_libraries(mygamestatic /usr/local/lib/libMyEngineStatic.a )

add_executable(mygamedynamic mygame.cpp)
target_link_libraries(mygamedynamic /usr/local/lib/libMyEngineDynamic.so)

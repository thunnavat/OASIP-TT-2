<script setup>

</script>
 
<template>
<div>  
  <div id="header">
    <nav id="nav-control" class="flex items-center justify-between flex-wrap p-6 bg-black	">
      <router-link :to="{name:'Home'}">
        <img src="./assets/logo.png" alt="logo" style="width: 54px; height: 54px;" viewBox="0 0 54 54" class="fill-current h-8 w-8 mr-2"> 
      </router-link>
      <div class="w-full block flex-grow sm:flex sm:items-center sm:w-auto">
        <div class="text-sm sm:flex-grow font-medium">
          <router-link :to="{name:'Home'}" class="block mt-4 sm:inline-block sm:mt-0 text-white hover:text-gray-300 mr-4">Home</router-link>
          <router-link :to="{name:'AboutUs'}" class="block mt-4 sm:inline-block sm:mt-0 text-white hover:text-gray-300 mr-4">About Us</router-link>
        </div> 
      </div>
    </nav>
  </div>
  <router-view> </router-view> 
</div>
</template>
 
<style>

</style>
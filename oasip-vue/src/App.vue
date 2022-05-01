<script setup>

</script>
 
<template>
<div>  
  <div id="header">
    <nav id="nav-control" class="flex items-center justify-between flex-wrap p-6 bg-black	">
      <router-link :to="{name:'Home'}">
        <span class="iconify" data-icon="noto:bear" style="width: 50px; height: 50px;"></span>
      </router-link>
      <div class="w-full block flex-grow sm:flex sm:items-center sm:w-auto">
        <div class="text-sm sm:flex-grow font-medium">
          <router-link :to="{name:'Home'}" class="block mt-4 sm:inline-block sm:mt-0 text-white hover:text-gray-300 ml-4">Home</router-link>
          <router-link :to="{name:'AboutUs'}" class="block mt-4 sm:inline-block sm:mt-0 text-white hover:text-gray-300 ml-4">About Us</router-link>
        </div> 
      </div>
    </nav>
  </div>
  <router-view> </router-view> 
</div>
</template>
 
<style>

</style>
<script setup>
import {ref , onBeforeMount} from 'vue'
import EventList from '../components/EventList.vue'

const getEvent = ref('')



</script>

<template>
<div>
  <div class="mt-4 flex justify-end">
    <button class="text-black">BOOKING</button>
    <!-- Select Bar -->
    <select id="select-bar" v-model="selectedGenre" class="select ml-4 mb-6 mt-3  text-red-500">
      <option option value="All" class="bg-black text-red-500 ">All</option>
      <option v-for="moviegenre in sortGenre" :key="movieGenre.id" class="bg-black text-red-500" ></option> 
    </select>
  </div>
  <!-- Show Event List -->
  <div class="absolute top-2/4">
    <h2 class="text-xl font-semibold ">EVENT LISTS</h2>
    <div class="box-border w-screen p-4 border-4">
      <div class="font-semibold align-middle text-center" v-show="getEvent === ''">
        EMPTY
      </div>
      <div v-show="getEvent !== ''">
        <event-list :event="getEvent"/>
      </div>
    </div>
  </div>
</div>
</template>
 
<style>

</style>
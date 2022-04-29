<script setup>
import {ref , onBeforeMount} from 'vue'
import EventList from '../components/EventList.vue'

const eventViews = ['ALL', 'DAY', 'CATEGORY', 'UPCOMING', 'PAST']
const events = ref([])

const getEvents = async () => {
  const res = await fetch('http://localhost:8080/api/events')
  if (res.status === 200) {
    events.value = await res.json()
    console.log('Get data')
  } else console.log('Error, cannot get data')
}
onBeforeMount(async () => {
  await getEvents()
})

</script>

<template>
<div>
  <div class="mt-4 flex justify-end">
    <button class="text-black">BOOKING</button>
    <!-- Select Bar -->
    <select id="select-bar" class="select ml-4 mb-6 mt-3  text-black">
      <option option value="ALL" v-for="(eventView, index) in eventViews" :key="index">{{ eventView }}</option> 
    </select>
  </div>
  <!-- Show Event List -->
  <div class="absolute top-2/4 m-2">
    <h2 class="text-xl font-semibold ">EVENT LISTS</h2>
    <div class="box-border w-screen p-4 border-t-8 border-black">
      <div class="font-semibold flex justify-center items-center text-black box-content bg-gray-300 h-96" v-if="events.length === 0">
        <span>EMPTY</span>
      </div>
      <div v-else>
        <event-list :events="events"/>
      </div>
    </div>
  </div>
</div>
</template>
 
<style>

</style>
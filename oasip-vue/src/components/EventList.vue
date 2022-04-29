<script setup>
import moment from 'moment'
const props = defineProps({
  events : {
   type: Array,
   default: []
  }
})
 const date = (date) => {
      return  moment(date).format('DD MMMM YYYY HH.mm');
    }
</script>
 
<template>
<div>
  <div class="grid grid-cols-4 gap-4 bg-gray-300">
    <div v-for="event in events" :key="event.id" class="bg-white m-4 px-4 py-2 border-2 rounded border-stone-900 text-sm">
      <ul>
        <li>CATEGORY: {{event.eventCategory.eventCategoryName}}</li>
        <li>NAME: {{event.bookingName}}</li>
        <li>START TIME: {{date(event.eventStartTime)}}</li>  
        <li>DURATION: {{event.eventDuration}} mins</li>
      </ul>
    </div>
  </div>
</div>
</template>
 
<style>

</style>
<script setup>
import moment from 'moment'
defineEmits(['detail'])
const props = defineProps({
  events : {
   type: Array,
   default: []
  }
})
 const date = (date) => {
      return  moment.utc(date).local().format('DD MMMM YYYY HH.mm');
    }
</script>
 
<template>
<div>
  <div class="grid grid-cols-4 gap-4 bg-[#c4c4c4]">
    <div v-for="event in events" :key="event.id" class="bg-white m-4 px-4 py-2 border-2 rounded border-stone-900 text-sm">
      <ul>
        <li>START TIME: {{date(event.eventStartTime)}}           </li>  
        <li>DURATION:   {{event.eventDuration}} mins             </li>
        <li>CATEGORY:   {{event.eventCategory.eventCategoryName}}</li>
        <li>NAME:       {{event.bookingName}}                    </li>
      </ul>
      <button class="flex justify-end mt-4 class= base-button ml-3 mb-4 bg-transparent border border-solid text-red-500 border-red-500 hover:bg-red-500 hover:text-black active:bg-red-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3" 
      @click="$emit('detail')">Detail</button>
    </div>
  </div>
</div>
</template>
 
<style>

</style>
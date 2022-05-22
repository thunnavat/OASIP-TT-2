<script setup>
defineEmits(['editEventCategory'])
defineProps({
  eventCategories : {
   type: Array,
   default: []
  }
})
</script>
 
<template>
<div>
  <div class="grid grid-cols-1 gap-1 bg-[#c4c4c4] p-4"> 
    <div v-for="eventCategory in eventCategories" :key="eventCategory.id"  class="bg-white m-4 px-4 pt-2 pb-4 leading-8 border-4 rounded border-stone-900 text-sm">
    <ul>
        <li class="flex justify-end font-bold ">
          <span class="text-[#878787] mr-1">CATEGORY NAME: </span>{{ eventCategory.eventCategoryName }}
        </li>
        <li class="font-bold">
          <span class="text-[#878787]">CATEGORY DESCRIPTION: </span>{{ eventCategory.eventCategoryDescription }}
        </li>
        <li class="font-bold">
          <span class="text-[#878787]">DURATION: </span>{{ eventCategory.eventDuration }}
        </li>  
        <button @click="$emit('editEventCategory', eventCategory)" class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3">
            EDIT</button>
    </ul>
</div>
  </div>
</div>
</template>
 
<style>

</style>
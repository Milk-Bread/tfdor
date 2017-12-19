<template>
	<div>
		<mt-picker :slots="slots"></mt-picker>
		<mt-spinner :size="100" color="#26a2ff" type="fading-circle"></mt-spinner>
	</div>
</template>

<script>
export default {
	data() {
		return {
			slots: [{
				values: ['年假', '事假', '病假', '婚假', '其他']
			}]
		};
	}
};
</script>

<style>

</style>
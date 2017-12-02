<template>
	<div>
		<mt-picker :slots="slots"></mt-picker>
		<router-view></router-view>
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
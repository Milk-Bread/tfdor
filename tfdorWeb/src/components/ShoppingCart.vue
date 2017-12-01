<template>
	<div>
		<div class="header-style">
			<header class="mint-header is-fixed">
				<div class="mint-header-button is-left">
				</div>
				<h1 class="mint-header-title">{{title}}</h1>
				<div class="mint-header-button is-right">
					<button class="mint-button mint-button--default mint-button--normal">
						<span class="mint-button-icon"><i class="mintui mintui-more"></i></span>
						<label class="mint-button-text"></label>
					</button>
				</div>
			</header>
		</div>
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
			}],
			title: '订单'
		};
	}
};
</script>

<style>

</style>
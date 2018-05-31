import React from 'react';
import ReactDOM from 'react-dom';

import { Layout, Menu, Breadcrumb, Icon } from 'antd';
const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

import 'antd/dist/antd.css';
import '../../../styles/base/_base.scss';

class FrontEndHeader extends React.Component {
	constructor(props) {
		super(props);
		this.onSelectMenuItem = this.onSelectMenuItem.bind(this);
	}

	onSelectMenuItem(e) {
		let oldSelectedItem = document.querySelector('.ant-menu-item-selected');

		if (oldSelectedItem) {
			oldSelectedItem.classList.remove('ant-menu-item-selected');
		}

		if (e.target.tagName == 'I') {
			e.target.parentElement.classList.add('ant-menu-item-selected');
		} else {
			e.target.classList.add('ant-menu-item-selected');
		}
	}

	render() {
		return (
			<Layout>
				<Header className="header">
					<div className="logo" style={{ float: 'left' }}>
						ChainService
					</div>

					<div style={{ float: 'right' }}>
						<ul
							className="ant-menu ant-menu-dark ant-menu-root ant-menu-horizontal"
							role="menu"
							style={{ lineHeight: '64px' }}
						>
							<li onClick={this.onSelectMenuItem} className="ant-menu-item right">
								<Icon type="appstore" />
							</li>
						</ul>
					</div>

					<div style={{ float: 'right', marginRight: '10%' }}>
						<ul
							className="ant-menu ant-menu-dark ant-menu-root ant-menu-horizontal"
							role="menu"
							style={{ lineHeight: '64px' }}
						>
							<li onClick={this.onSelectMenuItem} className="ant-menu-item right">
								Log in
							</li>
							<li onClick={this.onSelectMenuItem} className="ant-menu-item right">
								Sign Up
							</li>
						</ul>
					</div>
				</Header>
			</Layout>
		);
	}
}

export { FrontEndHeader };

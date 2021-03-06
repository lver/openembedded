DESCRIPTION = "Ralink 3070"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
PR = "r2"

inherit module

SRC_URI = "http://www.ralinktech.com.tw/data/drivers/2009_0525_RT3070_Linux_STA_v${PV}.tar.bz2 \
           file://makefile.patch \
	   file://config.patch \
	 "

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/2009_0525_RT3070_Linux_STA_v${PV}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/os/linux/rt3070sta.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -d ${D}/etc/Wireless/RT2870STA
	install -m 0644 ${S}/RT2870STA.dat ${D}/etc/Wireless/RT2870STA
	install -d ${D}/lib/firmware
	install -m 0644 ${S}/common/*.bin ${D}/lib/firmware/
}

PACKAGES =+ "${PN}-firmware"
FILES_${PN}-firmware = "/etc /lib/firmware"

RDEPENDS_${PN} = "${PN}-firmware"

SRC_URI[md5sum] = "705c5f8ba0f8a378218fef74859335f5"
SRC_URI[sha256sum] = "b2a24265bb29f4d429ec42fefbfc545b898c8c046c01c577ca08d9cc65c860d7"

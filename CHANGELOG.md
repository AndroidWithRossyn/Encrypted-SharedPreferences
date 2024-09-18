# Changelog

All notable changes to this project will be documented in this file.

## [1.01.03] - 2024-09-18
### Added
- New feature: **Encryption algorithm** now supports [AES-256](#).
- Added **automatic key rotation** for enhanced security.


### Fixed
- Resolved issue with **data corruption** during encryption.
- Fallback to regular SharedPreferences in case of encryption failure.
             `Crashing in Android Samsung Devices`
### Security
- Enhanced **protection** mechanisms to prevent brute force attacks.

## [1.0.0] - 2024-08-11
### Added
- Initial release with **basic encryption**.
- EncryptedSharedPreferences with AES256 encryption for keys and values
---

For detailed release notes, please refer to the [releases page](#).
